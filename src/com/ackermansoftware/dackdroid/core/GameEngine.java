package com.ackermansoftware.dackdroid.core;

import java.util.ArrayList;

import com.ackermansoftware.dackdroid.renderer.GameRenderer;

/**
 * GameEngine is the "main" function of the Dackdroid game engine. It creates
 * all the game components necessary to get the game started and manages the
 * threading for them. It also makes sure everything is cleaned up at the end
 * and that threads are recreated as necessary when the SurfaceView is
 * reinitialized.
 * 
 * @author dackerman
 * 
 */
public class GameEngine {

	private final int rendererFrameRate;
	private final int gameLogicFrameRate;

	public static class Stats {
		public long totalFramesRendered = 0;
		private long timeStarted = 0;

		public double getAverageFPS() {
			double nanosRunning = System.nanoTime() - timeStarted;
			return (1000000000 * totalFramesRendered) / nanosRunning;
		}

		public void start() {
			timeStarted = System.nanoTime();
		}

	}

	public Stats logicStats = new Stats();
	public Stats rendererStats = new Stats();

	// Controls underlying threads' event loop. Setting to false causes them to
	// finish their loop and return.
	private boolean enabled;

	private final GameRenderer renderer;
	private final GameLogic gameLogic;

	// Used to keep track of threads to make sure they all get cleaned up.
	private final ArrayList<GameThread> threads = new ArrayList<GameThread>();


	// Get the renderer and logic from the system.
	public GameEngine(GameSystem system, GameEngineSettings settings) {
		renderer = system.getRenderer();
		gameLogic = system.getGameLogic();

		// Apply settings
		this.rendererFrameRate = settings.rendererMaximumFps;
		this.gameLogicFrameRate = settings.gameLogicMaximumFps;
	}

	private void createThreads() {
		// Create game thread and rendering thread and add them to the list of
		// threads we are keeping track of. This way, we can make sure to kill
		// all when the game stops.
		GameThread gameCode = new GameThread(gameLogic, gameLogicFrameRate, logicStats);
		GameThread renderingThread = new GameThread(renderer, rendererFrameRate, rendererStats);

		// If we already ran the code, we may have dead threads
		// in the array.
		threads.clear();
		threads.add(gameCode);
		threads.add(renderingThread);
	}

	// Code that runs the game and/or rendering. On a separate thread from the
	// UI so they can do work independently and keep the input thread free of
	// blockages due to rendering.
	class GameThread extends Thread {
		private final ThreadedGameComponent gameComponent;
		private final int requestedMsPerFrame;
		private Stats stats = new Stats();

		public GameThread(ThreadedGameComponent renderer, int requestedFrameRate) {
			this.gameComponent = renderer;
			this.requestedMsPerFrame = 1000 / requestedFrameRate;
		}

		public GameThread(ThreadedGameComponent renderer, int requestedFrameRate, Stats stats) {
			this(renderer, requestedFrameRate);
			this.stats = stats;
		}

		@Override
		public void run() {
			// Main thread loop. Will die if the parent class makes itself
			// disabled.
			stats.start();
			while (enabled) {
				// We want to track framerate and slowdown the thread if
				// necessary.
				long startTime = System.nanoTime();

				gameComponent.executeFrame();
				stats.totalFramesRendered++;

				// If we finished early, wait until next frame.
				long endTime = System.nanoTime();
				long millisElapsed = (endTime - startTime) / 1000000;
				blockThread(millisElapsed);
			}
		}

		private void blockThread(long millisToExecute) {
			long millisToWait = requestedMsPerFrame - millisToExecute;
			if (millisToWait > 0) {
				try {
					Thread.sleep(millisToWait);
				} catch (InterruptedException e) {
					// If we got interrupted, just continue.
				}
			}
		}
	}

	public void enable() {
		enabled = true;
		// Create new threads for each component.
		createThreads();
		// Start all threads.
		for (GameThread thread : threads) {
			thread.start();
		}
	}

	public void disable() {
		enabled = false;
		blockUntilGameThreadFinishes();
	}

	private void blockUntilGameThreadFinishes() {
		// Kill each thread in serially. They should all be shutting down in
		// parallel, since setting enabled to false should be enough to get them
		// to stop their execution loops. The while loops just make sure they
		// are all properly dead.
		for (GameThread thread : threads) {
			while (thread.isAlive()) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					// Keep waiting even if we get rudely interrupted!
				}
			}
		}
		// Get rid of the dead threads. They are useless to us now.
		threads.clear();
	}

}
