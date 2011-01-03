package com.ackermansoftware.dackdroid.core;

import android.graphics.PointF;
import android.graphics.RectF;


public class CameraSystem {

	private final PointF cameraPosition = new PointF();

	public CameraSystem(PointF position) {
		setCameraPosition(position);
	}

	public PointF getCameraPosition() {
		synchronized (cameraPosition) {
			PointF clonePosition = new PointF();
			clonePosition.x = cameraPosition.x;
			clonePosition.y = cameraPosition.y;
			return clonePosition;
		}
	}

	public void setCameraPosition(PointF newCameraPosition) {
		synchronized (cameraPosition) {
			cameraPosition.x = newCameraPosition.x;
			cameraPosition.y = newCameraPosition.y;
		}
	}

	public RectF getCameraRect() {
		// TODO Auto-generated method stub
		return null;
	}
}
