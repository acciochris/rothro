package io.github.acciochris;

import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.Bounds;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;
import io.github.acciochris.framework.Camera;
import io.github.acciochris.framework.SimulationBody;
import io.github.acciochris.framework.SimulationFrame;
import org.dyn4j.world.World;

public class RoThro extends SimulationFrame {
	public static final double CAMERA_SCALE = 30.0;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	private Level level;

	private Ball p1;

	private RothroKeyListener keyListener;

	public RoThro(Level level) {
		super("RoThro", WIDTH, HEIGHT);
		this.level = level;
		p1 = new Ball();
		keyListener = new RothroKeyListener();
		super.canvas.setFocusable(true);
		super.canvas.addKeyListener(keyListener);
		super.canvas.requestFocusInWindow();
	}

	protected void initializeWorld() {
		this.world.setGravity(World.ZERO_GRAVITY);
		for (Obstacle obstacle : level.getObstacles()) {
			this.world.addBody(obstacle);
		}

		this.world.addBody(p1);
	}

	protected void initializeSettings() {
		setMousePanningEnabled(false);

		double width = WIDTH / CAMERA_SCALE;
		double height = HEIGHT / CAMERA_SCALE;
		Obstacle left = new Obstacle(new Rectangle(2.0, height), -width / 2 - 1, 0);
		Obstacle right = new Obstacle(new Rectangle(2.0, height), width / 2 + 1, 0);
		Obstacle bottom = new Obstacle(new Rectangle(width, 2.0), 0, -height / 2 - 1);
		Obstacle top = new Obstacle(new Rectangle(width, 2.0), 0, height / 2 + 1);
		this.world.addBody(left);
		this.world.addBody(right);
		this.world.addBody(bottom);
		this.world.addBody(top);
	}

	@Override
	protected void handleEvents()
	{
		super.handleEvents();
		p1.controls(keyListener.getIm().keysPressed);
	}

	@Override
	protected void initializeCamera(Camera camera) {
		super.initializeCamera(camera);
		camera.scale = CAMERA_SCALE;
	}
}
