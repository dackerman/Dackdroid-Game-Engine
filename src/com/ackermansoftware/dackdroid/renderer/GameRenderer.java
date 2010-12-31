package com.ackermansoftware.dackdroid.renderer;

import com.ackermansoftware.dackdroid.core.ThreadedGameComponent;

import android.util.Log;
import android.view.SurfaceHolder;

public class GameRenderer implements ThreadedGameComponent, RenderQueue {

	private final SurfaceHolder surfaceholder;
	private int value = 0;

	public GameRenderer(SurfaceHolder holder) {
		this.surfaceholder = holder;
	}

	@Override
	public void executeFrame() {
		Log.d("GameRenderer", "What up from the Renderer! value:" + value++);
	}

	@Override
	public void addToQueue(Renderable r) {
	}

	@Override
	public void clearRenderQueue() {
	}

}
