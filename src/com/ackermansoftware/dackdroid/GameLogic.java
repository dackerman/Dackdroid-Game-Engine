package com.ackermansoftware.dackdroid;

import java.util.ArrayList;

import com.ackermansoftware.dackdroid.core.ThreadedGameComponent;
import com.ackermansoftware.dackdroid.renderer.RenderQueue;

public class GameLogic implements ThreadedGameComponent {

	private final RenderQueue renderQueue;
	private final ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public GameLogic(RenderQueue renderer) {
		this.renderQueue = renderer;
		Tile tile = new Tile();
		gameObjects.add(tile);

	}

	@Override
	public void executeFrame() {
		for (GameObject gameObject : gameObjects) {
			gameObject.think(renderQueue);
		}
		renderQueue.frameComplete();
	}

}
