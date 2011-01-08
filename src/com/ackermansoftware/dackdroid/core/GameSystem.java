package com.ackermansoftware.dackdroid.core;

import android.content.Context;
import android.graphics.PointF;
import android.view.SurfaceHolder;

import com.ackermansoftware.dackdroid.renderer.GameRenderer;
import com.ackermansoftware.dackdroid.renderer.TextureLibrary;
import com.ackermansoftware.dackdroid.renderer.TextureLibraryImpl;
import com.ackermansoftware.forthorrors.levels.LevelFactory;
import com.ackermansoftware.forthorrors.levels.TileSystem;

/**
 * The GameSystem initializes and holds all of the services the various game
 * components might want. These are essentially global variables, but each
 * object can only access them through their public interface, thereby not
 * violating encapsulation.
 * 
 * @author dackerman
 * 
 */
public class GameSystem {
	private final GameRenderer renderer;
	private final GameLogic logic;
	private final CameraSystem cameraSystem;
	private final World world;
	private final TextureLibrary textures;
	private final TileSystem tileSystem;
	private final LevelFactory levelFactory;

	/**
	 * Construct and initialize all the objects for the game.
	 * 
	 * @param holder
	 *            SurfaceHolder for the rendering engine.
	 * @param context
	 *            Context for resource allocation.
	 */
	public GameSystem(SurfaceHolder holder, Context context) {
		cameraSystem = new CameraSystem(new PointF(0f, 0f));
		renderer = new GameRenderer(holder, context.getResources(), cameraSystem);
		world = new World();
		logic = new GameLogic(renderer, world);
		textures = new TextureLibraryImpl(context.getResources());
		tileSystem = new TileSystem(textures);
		levelFactory = new LevelFactory(context.getResources(), tileSystem);
	}

	public GameRenderer getRenderer() {
		return renderer;
	}


	public GameLogic getGameLogic() {
		return logic;
	}

	public CameraSystem getCameraSystem() {
		return cameraSystem;
	}

	public World getWorld() {
		return world;
	}

	public TextureLibrary getTextureLibrary() {
		return textures;
	}

	public TileSystem getTileSystem() {
		return tileSystem;
	}

	public LevelFactory getLevelFactory() {
		return levelFactory;
	}

}
