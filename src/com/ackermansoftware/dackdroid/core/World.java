package com.ackermansoftware.dackdroid.core;

import java.util.ArrayList;
import java.util.Iterator;

import com.ackermansoftware.dackdroid.gameobjects.GameObject;

public class World implements Iterable<GameObject> {
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public void addGameObject(GameObject o) {
		gameObjects.add(o);
	}

	public void clear() {
		gameObjects.clear();
	}

	@Override
	public Iterator<GameObject> iterator() {
		return gameObjects.iterator();
	}
}
