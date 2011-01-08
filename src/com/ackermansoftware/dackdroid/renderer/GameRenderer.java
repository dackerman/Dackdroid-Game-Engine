package com.ackermansoftware.dackdroid.renderer;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.SurfaceHolder;

import com.ackermansoftware.dackdroid.core.CameraSystem;
import com.ackermansoftware.dackdroid.core.ThreadedGameComponent;

public class GameRenderer implements ThreadedGameComponent, RenderQueue {

	private final SurfaceHolder surfaceholder;

	private ArrayList<Renderable> backBuffer = null;
	private ArrayList<Renderable> frontBuffer = null;

	// Two backing buffers for rendering. Every frame these render queues will
	// switch roles between frontBuffer and backBuffer.
	private final ArrayList<Renderable> buffer1 = new ArrayList<Renderable>();
	private final ArrayList<Renderable> buffer2 = new ArrayList<Renderable>();

	private final CameraSystem camera;

	// We will check this variable to see if we have already rendered the
	// current frame, in the case that the render thread is faster than the game
	// logic.
	private final AtomicBoolean alreadyRendered = new AtomicBoolean(false);

	public GameRenderer(SurfaceHolder holder, Resources res, CameraSystem camera) {
		this.surfaceholder = holder;
		this.camera = camera;

		// Set front and back buffers. Front buffer is being currently rendered,
		// and back buffer is the next frame which is getting populated with
		// Renderable objects by other threads.
		frontBuffer = buffer1;
		backBuffer = buffer2;
	}

	@Override
	public void executeFrame() {
		// If we already rendered the current frame, exit early!
		if (alreadyRendered.get()) {
			return;
		}
		final PointF cameraPos = camera.getCameraPosition();
		Canvas c = null;
		try {
			c = surfaceholder.lockCanvas(null);
			synchronized (surfaceholder) {
				// Nobody touch the frontBuffer right now!
				synchronized (frontBuffer) {
					c.drawColor(Color.BLACK);
					c.save();
					c.translate(-cameraPos.x, -cameraPos.y);
					for (Renderable r : frontBuffer) {
						r.render(c);
					}
					c.restore();
					alreadyRendered.set(true);
				}
			}
		} finally {
			if (c != null) {
				surfaceholder.unlockCanvasAndPost(c);
			}
		}
	}

	@Override
	public void addToQueue(Renderable r) {
		// Don't touch the backBuffer right now!
		synchronized (backBuffer) {
			backBuffer.add(r);
		}
	}

	@Override
	public void frameComplete() {
		// Swap buffers. Needs locks to both frontBuffer and backBuffer to avoid
		// 'splosions.
		ArrayList<Renderable> tmp = null;

		synchronized (frontBuffer) {
			synchronized (backBuffer) {
				tmp = frontBuffer;
				frontBuffer = backBuffer;
				backBuffer = tmp;

				// Clear out the backbuffer to get ready for the next frame.
				backBuffer.clear();
				alreadyRendered.set(false);
			}
		}
	}

}
