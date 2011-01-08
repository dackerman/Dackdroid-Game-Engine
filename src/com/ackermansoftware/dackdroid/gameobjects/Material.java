package com.ackermansoftware.dackdroid.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Canvas.EdgeType;

import com.ackermansoftware.dackdroid.renderer.Renderable;

public class Material implements Renderable {

	private PointF coordinates;
	private float rotation;
	private final Bitmap texture;

	public Material(Bitmap texture) {
		coordinates = new PointF();
		this.texture = texture;
	}

	public Material(PointF initialPosition, Bitmap texture) {
		setNewPosition(initialPosition);
		this.texture = texture;
	}

	public void setNewPosition(PointF newPosition) {
		coordinates.set(newPosition);
	}

	public void setRotation(float newRotation) {
		rotation = newRotation;
	}

	@Override
	public void render(Canvas c) {
		if (!c.quickReject(coordinates.x, coordinates.y, coordinates.x + texture.getWidth(),
				coordinates.y + texture.getHeight(), EdgeType.BW)) {

			// If we have rotation, we need to do a more complicated drawing
			// sequence.
			if (rotation != 0) {
				c.save();
				c.translate(coordinates.x, coordinates.y);
				c.rotate(rotation);
				c.drawBitmap(texture, 0, 0, null);
				c.restore();
			} else {
				c.drawBitmap(texture, coordinates.x, coordinates.y, null);
			}

		}
	}
}
