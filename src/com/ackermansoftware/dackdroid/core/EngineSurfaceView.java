package com.ackermansoftware.dackdroid.core;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class EngineSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	private GameEngine engine;
	private GameSystem system;

	private final Context context;

	public EngineSurfaceView(Context context) {
		super(context);
		this.context = context;
	}

	public EngineSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public EngineSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public void init(GameEngineSettings settings) {
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		system = new GameSystem(holder, context);
		engine = new GameEngine(system, settings);
	}

	public GameSystem getGameSystem() {
		if (system == null) {
			throw new RuntimeException(
			"gameSystem is null! Did you call init(GameSettings) on the gameView yet?");
		}
		return system;
	}

	public GameEngine getGameEngine() {
		if (system == null) {
			throw new RuntimeException(
			"gameEngine is null! Did you call init(GameSettings) on the gameView yet?");
		}
		return engine;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d("EngineSurfaceView", "Surface created - starting up game.");
		ensureSystemIsInitialized();
		engine.enable();
	}

	private void ensureSystemIsInitialized() {
		// If init() hasn't been called with custom settings, just call it with
		// the default settings.
		if (engine == null || system == null) {
			init(new GameEngineSettings());
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d("EngineSurfaceView", "Surface destroyed - ending game.");
		engine.disable();
	}

}
