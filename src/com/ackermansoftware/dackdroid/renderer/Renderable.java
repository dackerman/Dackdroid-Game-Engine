package com.ackermansoftware.dackdroid.renderer;

/**
 * Any objects that implements Renderable can be sent to the RenderQueue to be
 * drawn to the screen. GameObjects will hold onto these so they can draw
 * themselves every frame.
 * 
 * @author dackerman
 * 
 */
public interface Renderable{
	public void render();
}
