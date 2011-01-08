package com.ackermansoftware.dackdroid.renderer;

import android.graphics.Bitmap;

public interface TextureLibrary {

	/**
	 * Returns a Bitmap for the given resourceId. This value may be reused by
	 * other objects, or cached by the system so it may not be stored or
	 * modified. This function will throw an exception if there was no resource
	 * found with the given Id.
	 * 
	 * @param resourceId
	 *            R.drawable.someelement or equivalent from the system.
	 * @return Bitmap relating to the given Id.
	 */
	public Bitmap getTexture(int resourceId);

}