package com.ackermansoftware.dackdroid.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Canvas.EdgeType;

import com.ackermansoftware.dackdroid.renderer.Renderable;
import com.ackermansoftware.dackdroid.renderer.TextureLibrary;

public class Material implements Renderable {

	private PointF coordinates;
	private final int texture;

	public Material(int texture) {
		coordinates = new PointF();
		this.texture = texture;
	}

	public Material(PointF initialPosition, int texture) {
		coordinates = initialPosition;
		this.texture = texture;
	}

	public void setNewPosition(PointF newPosition) {
		coordinates = newPosition;
	}

	@Override
	public void render(TextureLibrary textures, Canvas c) {
		Bitmap icon = textures.getTexture(texture);
		if (!c.quickReject(coordinates.x, coordinates.y, coordinates.x + icon.getWidth(),
				coordinates.y + icon.getHeight(), EdgeType.BW)) {
			c.drawBitmap(icon, coordinates.x, coordinates.y, null);
		}
	}
}
