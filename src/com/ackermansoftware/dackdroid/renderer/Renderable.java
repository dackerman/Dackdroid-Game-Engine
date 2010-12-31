package com.ackermansoftware.dackdroid.renderer;

import android.graphics.Canvas;

/**
 * Any objects that implements Renderable can be sent to the RenderQueue to be
 * drawn to the screen. GameObjects will hold onto these so they can draw
 * themselves every frame.
 * 
 * @author dackerman
 * 
 */
public interface Renderable{

	/**
	 * This function will be called on every frame that it is in the
	 * RenderQueue. It will be supplied a TextureLibrary so it can get textures
	 * from the system, and a Canvas object to draw upon. All synchronization
	 * and threading is handled, as long as all drawing is done in this
	 * function.
	 * 
	 * @param textures
	 *            TextureLibrary for finding any bitmap in the system.
	 * @param c
	 *            Canvas with which to draw upon.
	 */
	public void render(TextureLibrary textures, Canvas c);
}
