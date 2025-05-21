package io.github.acciochris;

import java.awt.Color;
import java.util.*;
import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.Bounds;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.Joint;
import org.dyn4j.dynamics.joint.*;
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
	private List<PrismaticJoint<SimulationBody>> prisJoints;
	private List<RevoluteJoint<SimulationBody>> revJoints;

	private Level level;

	private Ball p1;

	private RothroKeyListener keyListener;

	public RoThro(Level level) {
		super("RoThro", WIDTH, HEIGHT);
		this.level = level;
		p1 = new Ball(level.getBallRadius());
		p1.translate(level.getBallPos());
		prisJoints = new ArrayList<PrismaticJoint<SimulationBody>>();
		revJoints = new ArrayList<RevoluteJoint<SimulationBody>>();
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
					String jointType = obs.getJointType();

					if (jointType.equals("Revolute"))
					{
						obs.setAngularDamping(1.0);
						Circle fulcrum = Geometry.createCircle(0.1);
						Obstacle anchor = new Obstacle(fulcrum, obsX, obsY, new Color(0xFFB86C), false, "", "");
						RevoluteJoint<SimulationBody> rj = new RevoluteJoint<SimulationBody>(obs, anchor, new Vector2(obsX, obsY));
						revJoints.add(rj);
						rj.setCollisionAllowed(false);
						this.world.addBody(anchor);
						this.world.addJoint(rj);
					}

					else if (jointType.equals("Distance"))
					{

					}

					else if (jointType.equals("Weld"))
					{

					}

					else if (jointType.equals("Pin"))
					{

					}

					else if (jointType.equals("Prismatic"))
					{
        				Obstacle rectBody = new Obstacle(new Rectangle(2.75, 3.25), -2.0, -6.0, new Color(90, 40, 180), true, "", "FIXANG");

						Obstacle anchor1 = new Obstacle(new Circle(0.1), -2.0, 0.0, false);
						//Obstacle anchor2 = new Obstacle(new Circle(0.1), -2.0, -5.0, false);

						PrismaticJoint<SimulationBody> pj1 = new PrismaticJoint<SimulationBody>(anchor1, rectBody, new Vector2(rectBody.getX(), rectBody.getY()), new Vector2(0, 1.0));
						PrismaticJoint<SimulationBody> pj2 = new PrismaticJoint<SimulationBody>(anchor1, obs, new Vector2(obsX, obsY), new Vector2(0, 1.0));

						prisJoints.add(pj1);
						prisJoints.add(pj2);

						obs.setLinearVelocity(obs.getLinearVelocity().getYComponent());
						rectBody.setLinearVelocity(rectBody.getLinearVelocity().getYComponent());

						pj1.setCollisionAllowed(false);
						pj2.setCollisionAllowed(false);
						pj1.setMotorEnabled(true);
						pj1.setMotorSpeed(-5.0);
						pj2.setMotorEnabled(true);
						pj2.setMotorSpeed(5.0);
						pj1.setMaximumMotorForceEnabled(true);
						pj1.setMaximumMotorForce(100.0);
						pj2.setMaximumMotorForceEnabled(true);
						pj2.setMaximumMotorForce(100.0);
						//pj1.setLimitsEnabled(-height / 2, height / 2);
						//pj2.setLimitsEnabled(-height / 2, height / 2);

						this.world.addBody(rectBody);
						this.world.addBody(anchor1);
						//this.world.addBody(anchor2);
						this.world.addJoint(pj1);
						this.world.addJoint(pj2);
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

		for (PrismaticJoint<SimulationBody> pj : prisJoints)
		{
			Obstacle jointBody = (Obstacle)pj.getBody(1);
			double bodyY = jointBody.getShape().getRadius() + jointBody.getWorldCenter().y;
			if (bodyY == height || -bodyY == -height)
			{
				pj.setMotorSpeed(-pj.getMotorSpeed());
				pj.setMaximumMotorForce(-pj.getMaximumMotorForce());
			}
		}
		
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
