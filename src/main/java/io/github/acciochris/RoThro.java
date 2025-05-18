package io.github.acciochris;

import java.util.List;
import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.Bounds;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.Joint;
import org.dyn4j.dynamics.joint.RevoluteJoint;
import org.dyn4j.geometry.*;
import io.github.acciochris.framework.Camera;
import io.github.acciochris.framework.SimulationBody;
import io.github.acciochris.framework.SimulationFrame;
import org.dyn4j.world.World;

// Dracula Color Palette
//
// Background
// #282A36
// Current Line
// #44475A
// Foreground
// #F8F8F2
// Comment
// #6272A4
// Cyan
// #8BE9FD
// Green
// #50FA7B
// Orange
// #FFB86C
// Pink
// #FF79C6
// Purple
// #BD93F9
// Red
// #FF5555
// Yellow
// #F1FA8C

public class RoThro extends SimulationFrame {
	public static final double CAMERA_SCALE = 30.0;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	private final double width = WIDTH / CAMERA_SCALE;
	private final double height = HEIGHT / CAMERA_SCALE;

	private Level level;

	private Ball p1;

	private RothroKeyListener keyListener;

	public RoThro(Level level) {
		super("RoThro", WIDTH, HEIGHT);
		this.level = level;
		p1 = new Ball(level.getBallRadius());
		p1.translate(level.getBallPos());
		keyListener = new RothroKeyListener();
		super.canvas.setFocusable(true);
		super.canvas.addKeyListener(keyListener);
		super.canvas.requestFocusInWindow();
	}

	protected void initializeWorld() {
		this.world.setGravity(World.ZERO_GRAVITY);
		List<List<Obstacle>> obstacles = level.getObstacles();
		for (List<Obstacle> obstacle : obstacles) {
			for (Obstacle obs : obstacle)
			{
				this.world.addBody(obs);
			}
		}
		
		if (level.hasJoints())
		{
			for (Integer joint : level.getJoints())
			{
				for (Obstacle obs : obstacles.get(joint))
				{
					double obsX = obs.getX();
					double obsY = obs.getY();
					if (obs.getJointType().equals("Revolute"))
					{
						Polygon fulcrum = Geometry.createEquilateralTriangle(0.1);
						fulcrum.translate(obsX, obsY);
						Obstacle anchor = new Obstacle(fulcrum, obsX, obsY, false);
						Vector2 anchorPos = new Vector2(obsX, obsY);
						RevoluteJoint<SimulationBody> rj = new RevoluteJoint<SimulationBody>(obs, anchor, anchorPos);
						rj.setLimitsEnabled(true);
						obs.setGravityScale(1.0);
						rj.setLimits(-Math.PI / 3, Math.PI / 3);
						this.world.addBody(anchor);
						this.world.addJoint(rj);
					}
				}
			}
		}

		this.world.addBody(p1);
	}

	protected void initializeSettings() {
		setMousePanningEnabled(false);
		setMousePickingEnabled(false);

		Obstacle left = new Obstacle(new Rectangle(1.0, height), -width / 2, 0, false);
		Obstacle bottom = new Obstacle(new Rectangle(width, 1.0), 0, -height / 2, false);
		Obstacle top = new Obstacle(new Rectangle(width, 1.0), 0, height / 2, false);
		this.world.addBody(left);
		this.world.addBody(bottom);
		this.world.addBody(top);
		addHole();
	}

	private void addHole() {
		Hole hole = level.getHole();
		if (hole == null) {
			this.world.addBody(new Obstacle(new Rectangle(1.0, height), width / 2, 0, false));
			return;
		}

		Obstacle upper = new Obstacle(
			new Rectangle(1.0, hole.getY() - hole.getSize() / 2),
			width / 2,
			(height - hole.getY() + hole.getSize() / 2) / 2, false
		);
		Obstacle lower = new Obstacle(
			new Rectangle(1.0, height - hole.getY() - hole.getSize() / 2),
			width / 2,
			(-hole.getY() - hole.getSize() / 2) / 2, false
		);
		this.world.addBody(upper);
		this.world.addBody(lower);
	}

	@Override
	protected void gameLoopLogic() {
		super.gameLoopLogic();
		Vector2 ballCoords = p1.getWorldCenter();
		// FIXME: hard-coded ball radius
		if (Math.abs(ballCoords.x) > width / 2 + 1.0 || Math.abs(ballCoords.y) > height / 2 + 1.0) {
			this.stop();
		}
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
