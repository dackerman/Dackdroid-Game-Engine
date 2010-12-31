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
	/**
	 * Add a Renderable to the renderQueue for this frame. This may be called
	 * any number of times before a frameComplete() call.
	 * 
	 * @param r
	 *            Renderable object which should be rendered this frame.
	 */
	public void addToQueue(Renderable r);

	/**
	 * Tells the RenderQueue that this frame has no more draw calls. In a
	 * double-buffered system, this tells the renderer that it should swap
	 * buffers and render the scene that was just built.
	 */
	public void frameComplete();
}
