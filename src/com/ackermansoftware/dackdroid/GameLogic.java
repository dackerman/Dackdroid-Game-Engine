package com.ackermansoftware.dackdroid;

import com.ackermansoftware.dackdroid.core.ThreadedGameComponent;
import com.ackermansoftware.dackdroid.renderer.RenderQueue;

import android.util.Log;

public class GameLogic implements ThreadedGameComponent {

	private final RenderQueue renderer;
	private int value = 0;

	public GameLogic(RenderQueue renderer) {
		this.renderer = renderer;
	}

	@Override
	public void executeFrame() {
		Log.d("GameLogic", "Howdy, from the GameLogic thread! value:"+value++);
	}

}
