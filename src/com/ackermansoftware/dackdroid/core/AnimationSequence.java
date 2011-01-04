package com.ackermansoftware.dackdroid.core;


public class AnimationSequence {
	private final AnimationFrame[] frames;

	private final int capacity;
	private int size;
	private int totalAnimationDuration;
	private long startTime = -1;

	// Set to true if animation should keep looping until reset.
	public boolean looping = false;

	// Set to true if animation should return to first frame after the animation
	// finishes (only for non-looping animations).
	public boolean returnToBeginning = true;

	/**
	 * Create a new AnimationSequence, with the given number of frames.
	 * 
	 * @param numFrames
	 *          Number of frames the AnimationSequence will contain.
	 */
	public AnimationSequence(int numFrames) {
		frames = new AnimationFrame[numFrames];
		capacity = numFrames;
		size = 0;
	}

	public void addFrame(AnimationFrame frame) {
		assert size < capacity;
		frames[size] = frame;
		size++;
		totalAnimationDuration += frame.duration;
	}

	public void startAnimation() {
		startTime = getTime();
	}

	public void resetAnimation() {
		startTime = -1;
	}

	/**
	 * Get the current frame of animation, taking into account the settings for
	 * returnToBeginning and looping.
	 * 
	 * @return The AnimationFrame for the current time.
	 */
	public AnimationFrame getFrame() {
		long time = getTime();
		assert size > 0;
		if (notAnimating()) {
			return firstFrame();
		}

		final long timeOffset = calculateTimeOffset(time);

		float frameStartTime = 0f;
		AnimationFrame returnFrame = null;
		for (int i=0;i<size;i++) {
			returnFrame = frames[i];
			frameStartTime += frames[i].duration;
			if (frameStartTime > timeOffset) {
				break;
			}
		}
		// If we want the animation to go back to the beginning, then reset if we
		// are done.
		if (frameStartTime < timeOffset) {
			if (returnToBeginning) {
				resetAnimation();
				return firstFrame();
			}
		}

		return returnFrame;
	}


	private long getTime() {
		return System.nanoTime() / 1000000;
	}

	private long calculateTimeOffset(long time) {
		long timeOffset = time - startTime;
		// If we are looping the animation, then wrap to beginning if we past the
		// last frame.
		if (looping) {
			timeOffset %= totalAnimationDuration;
		}
		return timeOffset;
	}

	private boolean notAnimating() {
		return startTime < 0;
	}

	private AnimationFrame firstFrame() {
		return frames[0];
	}

}
