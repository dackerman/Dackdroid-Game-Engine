package com.ackermansoftware.dackdroid.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Canvas.EdgeType;

import com.ackermansoftware.dackdroid.renderer.Renderable;
import com.ackermansoftware.dackdroid.renderer.TextureLibrary;

public class Material implements Renderable {

	private PointF coordinates;
	private float rotation;
	private final int textureId;
	private Bitmap texture;

	public Material(int texture) {
		coordinates = new PointF();
		this.textureId = texture;
	}

	public Material(PointF initialPosition, int texture) {
		setNewPosition(initialPosition);
		this.textureId = texture;
	}

	public void setNewPosition(PointF newPosition) {
		coordinates.set(newPosition);
	}

	public void setRotation(float newRotation) {
		rotation = newRotation;
	}

	@Override
	public void render(TextureLibrary textures, Canvas c) {
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

	@Override
	public void beforeRender(TextureLibrary textures) {
		texture = textures.getTexture(textureId);
	}
}
