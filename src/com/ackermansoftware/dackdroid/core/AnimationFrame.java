package com.ackermansoftware.dackdroid.core;

import android.graphics.Bitmap;

public class AnimationFrame {
	public Bitmap drawable;
	public long duration;

	/**
	 * @param flyingBird1
	 *            The ID of the drawable to render on the screen.
	 * @param duration
	 *            The duration of this frame in milliseconds.
	 */
	public AnimationFrame(Bitmap flyingBird1, long duration) {
		this.drawable = flyingBird1;
		this.duration = duration;
	}
}
