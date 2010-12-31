package com.ackermansoftware.dackdroid;

import com.ackermansoftware.dackdroid.renderer.RenderQueue;

public class Tile extends GameObject {

	private final Material material = new Material();

	@Override
	public void think(RenderQueue renderQueue) {
		renderQueue.addToQueue(material);
	}
}
