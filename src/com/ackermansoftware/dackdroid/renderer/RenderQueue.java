package com.ackermansoftware.dackdroid.renderer;

/**
 * The RenderQueue is the interface all GameObjects see when they want to submit
 * something for rendering on the next frame. All Renderable objects are put in
 * the render queue for the next frame.
 * 
 * @author dackerman
 * 
 */
public interface RenderQueue {
	public void addToQueue(Renderable r);

	public void clearRenderQueue();
}
