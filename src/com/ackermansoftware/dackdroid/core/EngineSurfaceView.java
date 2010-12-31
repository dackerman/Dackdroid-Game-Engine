package com.ackermansoftware.dackdroid.core;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class EngineSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	private GameEngine engine;

	public EngineSurfaceView(Context context) {
		super(context);
		init();
	}

	public EngineSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public EngineSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init(){
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		engine = new GameEngine(holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d("EngineSurfaceView", "Surface created - starting up game.");
		engine.enable();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d("EngineSurfaceView", "Surface destroyed - ending game.");
		engine.disable();
	}

}
