package com.ackermansoftware.dackdroid.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.ackermansoftware.dackdroid.exceptions.InvalidResourceException;

public class TextureLibraryImpl implements TextureLibrary, TexturePopulator {
	Resources res;
	int lastId = 0;
	Bitmap lastBmp;

	private final Map<Integer, ArrayList<BitmapHolder>> toPopulate = new HashMap<Integer, ArrayList<BitmapHolder>>();

	public TextureLibraryImpl(Resources res) {
		this.res = res;
	}

	Map<Integer, Bitmap> cache = new HashMap<Integer, Bitmap>();

	/* (non-Javadoc)
	 * @see com.ackermansoftware.dackdroid.renderer.TextureLibraryInterface#getTexture(int)
	 */
	public Bitmap getTexture(int resourceId) {
		// Optimization - keep track of last id for fast repeat access. No
		// synchronization crap, and no HashMap lookup.
		if (resourceId == lastId) {
			return lastBmp;
		}

		synchronized (cache) {
			Bitmap b;
			Integer id = resourceId;
			if (cache.containsKey(id)) {
				b = cache.get(id);
			} else {
				Log.d("TextureLibrary", "Resource " + id + " not in cache... fetching");
				b = BitmapFactory.decodeResource(res, id);
				if (b == null) {
					throw new InvalidResourceException();
				}
				cache.put(id, b);
			}
			lastId = resourceId;
			lastBmp = b;
			return b;
		}
	}

	public void populateTextures() {
		for(Integer res : toPopulate.keySet()) {
			ArrayList<BitmapHolder> a = toPopulate.get(res);
			Bitmap tex = getTexture(res);
			for(int i=0;i<a.size();i++) {
				BitmapHolder b = a.get(i);
				b.setBitmap(tex);
			}
		}
	}

	@Override
	public void requestTexture(int resourceId, BitmapHolder holder) {
		Integer i = resourceId;
		ArrayList<BitmapHolder> a;
		if(toPopulate.containsKey(i)) {
			a = toPopulate.get(i);
		}else {
			a = new ArrayList<BitmapHolder>();
		}
		a.add(holder);
	}
}