package io.github.acciochris;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;
import io.github.acciochris.framework.Camera;
import io.github.acciochris.framework.SimulationBody;
import io.github.acciochris.framework.SimulationFrame;
import org.dyn4j.world.World;

public class RoThro extends SimulationFrame {
	private Level level;

	public RoThro(Level level) {
		super("RoThro");
		this.level = level;
	}

	protected void initializeWorld() {
		for (Obstacle obstacle : level.getObstacles()) {
			this.world.addBody(obstacle);
		}

		Ball ball = new Ball();
		this.world.addBody(ball);
	}

	@Override
	protected void initializeCamera(Camera camera) {
		super.initializeCamera(camera);
		camera.scale = 45.0;
	}
}
