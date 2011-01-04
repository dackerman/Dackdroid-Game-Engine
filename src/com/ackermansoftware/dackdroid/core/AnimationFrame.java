package com.ackermansoftware.dackdroid.core;

public class AnimationFrame {
	public int drawable;
	public long duration;

	/**
	 * @param resourceId
	 *          The ID of the drawable to render on the screen.
	 * @param duration
	 *          The duration of this frame in milliseconds.
	 */
	public AnimationFrame(int resourceId, long duration) {
		this.drawable = resourceId;
		this.duration = duration;
	}
}
