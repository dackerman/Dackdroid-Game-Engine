package com.ackermansoftware.dackdroid.renderer;

import java.util.HashMap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.ackermansoftware.dackdroid.exceptions.InvalidResourceException;

public class TextureLibrary {
	Resources res;

	public TextureLibrary(Resources res) {
		this.res = res;
	}

	HashMap<Integer, Bitmap> cache = new HashMap<Integer, Bitmap>();

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
	public Bitmap getTexture(int resourceId) {
		Bitmap b;
		if (cache.containsKey(resourceId)) {
			b = cache.get(resourceId);
		} else {
			Log.d("TextureLibrary", "Resource " + resourceId + " not in cache... fetching");
			b = BitmapFactory.decodeResource(res, resourceId);
			if (b == null) {
				throw new InvalidResourceException();
			}
			cache.put(resourceId, b);
		}
		return b;
	}
}
