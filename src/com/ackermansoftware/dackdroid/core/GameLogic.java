package com.ackermansoftware.dackdroid.core;

import com.ackermansoftware.dackdroid.gameobjects.GameObject;
import com.ackermansoftware.dackdroid.renderer.RenderQueue;

public class GameLogic implements ThreadedGameComponent {

	private final RenderQueue renderQueue;
	private final World world;

	public GameLogic(RenderQueue renderer, World world) {
		this.renderQueue = renderer;
		this.world = world;
	}

	@Override
	public void executeFrame() {
		executeThinkPhase();
		executeUpdateStatePhase();
		executeRenderPhase();
		renderQueue.frameComplete();
	}

	private void executeThinkPhase() {
		for (GameObject gameObject : world) {
			gameObject.think();
		}
	}

	private void executeUpdateStatePhase() {
		for (GameObject gameObject : world) {
			gameObject.updateState();
		}
	}

	private void executeRenderPhase() {
		for (GameObject gameObject : world) {
			gameObject.render(renderQueue);
		}
	}
}
