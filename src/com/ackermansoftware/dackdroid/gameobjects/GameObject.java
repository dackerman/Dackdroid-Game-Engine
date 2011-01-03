package com.ackermansoftware.dackdroid.gameobjects;

import com.ackermansoftware.dackdroid.renderer.RenderQueue;

/**
 * Main object to be inherited from by all objects that interact in the game. It
 * will get called upon multiple times throughout the lifetime of a frame, in
 * the order shown below. The class may skip any step it doesn't need, but may
 * use them all if necessary.
 * 
 * @author dackerman
 * 
 */

public abstract class GameObject {

	/**
	 * This is the <b>first function executed in the pipeline</b>. Calculate any
	 * new velocities or accelerations, or apply damage to NPCs, or other
	 * interactions between objects. However, if your object needs to know about
	 * the state of other objects, it may not be able to calculate it here. In
	 * that case, see the updateState() function.
	 */
	public void think() {}

	/**
	 * <b>Second in the pipeline of execution<b/>, this function is where the
	 * updating of each object's state takes place. This doesn't always happen
	 * in think() because some objects need to know the state of other objects
	 * before they know what to do. <b>Physics simulations</b> are one example
	 * that need to know all the interactions before it can update the positions
	 * of each object.
	 */
	public void updateState() {}

	/**
	 * This function is where the object will <b>draw itself</b>. The drawing
	 * won't happen immediately; it will be sent to the render queue and be
	 * rendered the next time the render thread is ready for the frame. This
	 * frame may get dropped entirely if the render thread is taking to long to
	 * process frames.
	 * 
	 * @param renderQueue
	 *            RenderQueue with which to add Renderable objects for this
	 *            frame.
	 */
	public void render(RenderQueue renderQueue) {}


}
