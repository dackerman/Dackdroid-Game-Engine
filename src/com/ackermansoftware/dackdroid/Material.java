package com.ackermansoftware.dackdroid;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ackermansoftware.dackdroid.renderer.Renderable;
import com.ackermansoftware.dackdroid.renderer.TextureLibrary;

public class Material implements Renderable {

	@Override
	public void render(TextureLibrary textures, Canvas c) {
		Bitmap icon = textures.getTexture(R.drawable.icon);
		c.drawBitmap(icon, 0, 0, null);
	}

}
