package io.github.acciochris;

import java.awt.Color;
import java.util.*;
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


/**
 * Core logic for the RoThro game.
 * 
 * @author Chris Liu
 * @author Anvesh Pattaje
 * @author Justin Huang
 * @version May 30, 2025
 */
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
	// private Arm arm1;
	// private Arm arm2;
	// private Avatar avatar;
	// private RevoluteJoint<SimulationBody> armJoint1;
	// private RevoluteJoint<SimulationBody> armJoint2;

	private RothroKeyListener keyListener;

	/**
	 * Construct new RoThro object from a level definition.
	 * 
	 * @param level definition for the current level
	 */
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

		// arm1 = new Arm(new Vector2(-1, 1));
		// arm2 = new Arm(new Vector2(1,1));
		// avatar = new Avatar(armJoint1, armJoint2);
	}


	/**
	 * Initialize the world by adding relevant obstacles, balls (and avatars).
	 */
	protected void initializeWorld() {
		this.world.setGravity(World.ZERO_GRAVITY);
		List<List<Obstacle>> obstacles = level.getObstacles();
		for (List<Obstacle> obstacle : obstacles) {
			for (Obstacle obs : obstacle)
			{
				this.world.addBody(obs);
			}
		}
		
		// this.world.addBody(arm1);
		// this.world.addBody(arm2);
		// arm1.translate(-3,1);
		// arm2.translate(3,1);
		// this.world.addBody(avatar);
		// armJoint1 = new RevoluteJoint<SimulationBody>(avatar, arm1, new Vector2(-1,1));
        // armJoint2 = new RevoluteJoint<SimulationBody>(avatar, arm2, new Vector2(1,1));
		// this.world.addJoint(armJoint1);
        // this.world.addJoint(armJoint2);

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
        				Obstacle rectBody = new Obstacle(new Rectangle(2.75, 3.25), -2.0, -4.0, new Color(90, 40, 180), true, "", "FIXANG");

						Obstacle anchor1 = new Obstacle(new Circle(0.01), -2.0, 0.0, false);
						anchor1.getFixture(0).setSensor(true);

						Vector2 anchorPnt = new Vector2(anchor1.getX(), anchor1.getY());
						Vector2 axis = new Vector2(0, 1.0);

						PrismaticJoint<SimulationBody> pj1 = new PrismaticJoint<SimulationBody>(anchor1, obs, anchorPnt, axis);
						PrismaticJoint<SimulationBody> pj2 = new PrismaticJoint<SimulationBody>(anchor1, rectBody, anchorPnt, axis);

						prisJoints.add(pj1);
						prisJoints.add(pj2);

						obs.setLinearVelocity(new Vector2(0, -10.0));
						rectBody.setLinearVelocity(new Vector2(0, 7.5));
						pj1.setMotorSpeed(-10.0);
						pj2.setMotorSpeed(7.5);
						pj1.setMaximumMotorForceEnabled(true);
						pj1.setMaximumMotorForceEnabled(true);
						pj1.setMaximumMotorForce(1000);
						pj2.setMaximumMotorForce(750);

						this.world.addBody(rectBody);
						this.world.addBody(anchor1);
						this.world.addJoint(pj1);
						this.world.addJoint(pj2);
					}
				}
			}
		}

		this.world.addBody(p1);
	}

	/**
	 * Initialize several settings. Also add barriers to the four sides and
	 * create the target hole.
	 */
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

	/**
	 * Helper method for adding the whole to the right wall.
	 */
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

	/**
	 * Stop the simulation and proceed to the next level when we detect that the
	 * ball has gone through the hole. This method is called for every frame.
	 */
	@Override
	protected void gameLoopLogic() {
		super.gameLoopLogic();
		Vector2 ballCoords = p1.getWorldCenter();

		// FIXME: hard-coded ball radius
		if (Math.abs(ballCoords.x) > width / 2 + 1.0 || Math.abs(ballCoords.y) > height / 2 + 1.0) {
			this.stop();
		}
	}

	/**
	 * Handle relevant events with respect to shooting the ball.
	 */
	@Override
	protected void handleEvents()
	{
		super.handleEvents();
		p1.controls(keyListener.getIm().getKeysPressed());
	}

	/**
	 * Initialize the viewport with the correct camera scale.
	 * 
	 * @param camera the camera object
	 */
	@Override
	protected void initializeCamera(Camera camera) {
		super.initializeCamera(camera);
		camera.scale = CAMERA_SCALE;
	}
}
