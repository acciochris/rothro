package io.github.acciochris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.contact.Contact;
import org.dyn4j.dynamics.contact.ContactConstraint;
import org.dyn4j.dynamics.joint.*;
import org.dyn4j.geometry.*;
import io.github.acciochris.framework.Camera;
import io.github.acciochris.framework.SimulationBody;
import io.github.acciochris.framework.SimulationFrame;
import org.dyn4j.world.ContactCollisionData;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.ContactListener;
import org.dyn4j.world.listener.ContactListenerAdapter;

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
	private List<RevoluteJoint<SimulationBody>> pJoints;

	private Level level;

	private Ball ball;
	private Avatar p1;


	private RothroKeyListener keyListener;

	/**
	 * Construct new RoThro object from a level definition.
	 * 
	 * @param level definition for the current level
	 */
	public RoThro(Level level) {
		super("RoThro", WIDTH, HEIGHT);
		this.level = level;
		ball = new Ball(level.getBallRadius());
		ball.translate(level.getBallPos());
		p1 = new Avatar();
		p1.translate(level.getBallPos().x - ball.getRadius() - 1, level.getBallPos().y);

		prisJoints = new ArrayList<PrismaticJoint<SimulationBody>>();
		revJoints = new ArrayList<RevoluteJoint<SimulationBody>>();

		keyListener = new RothroKeyListener();
		super.canvas.setFocusable(true);
		super.canvas.addKeyListener(keyListener);
		super.canvas.requestFocusInWindow();
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
		
		this.world.addContactListener(new ContactListenerAdapter<SimulationBody>() {
			@Override
			public void begin(ContactCollisionData<SimulationBody> col, Contact c)
			{
				if (level.getLevelNum() != 2)
					return;
				SimulationBody body1 = col.getBody1();
				SimulationBody body2 = col.getBody2();
				if (body1 instanceof Avatar && body2 instanceof Obstacle)
				{
					((Obstacle)body2).setVisible(true);
				}
				else if (body2 instanceof Avatar && body1 instanceof Obstacle)
				{
					((Obstacle)body1).setVisible(true);
				}
			}
		});

		if (level.hasJoints())
		{
			for (RoThroJoint joint : level.getJoints())
			{
				String jointType = joint.getType();

				if (jointType.equals("Revolute"))
				{
					addRevJoint(joint);
				}

				else if (jointType.equals("Distance"))
				{
					addDistJoint(joint);
				}

				else if (jointType.equals("Pendulum"))
				{
					addPendJoint(joint);
				}

				else if (jointType.equals("Prismatic"))
				{
					addPrisJoint(joint);
				}
			}
		}
		this.world.addBody(ball);
		this.world.addBody(p1);
	}

	/**
	 * 
	 */
	private class WallObstacle extends Obstacle {
		public WallObstacle(
			Convex shape,
			double x,
			double y,
			boolean canMove,
			int level)
		{
			super(shape, x, y, canMove, level);
		}

		@Override
		public void setVisible(boolean visible)
    	{
        	this.visible = true;
    	}
	}

	/**
	 * Initialize several settings. Also add barriers to the four sides and
	 * create the target hole.
	 */
	protected void initializeSettings() {
		setMousePanningEnabled(false);
		setMousePickingEnabled(false);

		int levelNum = level.getLevelNum();

		Obstacle left = new WallObstacle(new Rectangle(1.0, height), -width / 2, 0, false, levelNum);
		Obstacle bottom = new WallObstacle(new Rectangle(width, 1.0), 0, -height / 2, false, levelNum);
		Obstacle top = new WallObstacle(new Rectangle(width, 1.0), 0, height / 2, false, levelNum);
		this.world.addBody(left);
		this.world.addBody(bottom);
		this.world.addBody(top);
		addHole();
	}

	private void addPrisJoint(RoThroJoint pris)
	{
		Obstacle body = pris.getBody2();
		Obstacle anchor = pris.getBody1();
		PrismaticJoint<SimulationBody> pj = new PrismaticJoint<SimulationBody>(anchor, body, pris.getAnchorPnt1(), pris.getAxis());
		if (pris.getLimEn())
		{
			pj.setLimitsEnabled(pris.getLowerLimit(), pris.getUpperLimit());
		}

		body.setLinearVelocity(0.0, pris.getBody2Speed());

		this.world.addJoint(pj);
	}

	private void addRevJoint(RoThroJoint rev)
	{
		Obstacle b1 = rev.getBody1();
		Obstacle b2 = rev.getBody2();
		b2.setAngularDamping(rev.getAngularDamping());
		RevoluteJoint<SimulationBody> rj = new RevoluteJoint<SimulationBody>(b2, b1, rev.getAnchorPnt1());
		revJoints.add(rj);
		if (rev.getLimEn())
		{
			rj.setLimitsEnabled(rev.getLowerLimit(), rev.getUpperLimit());
		}
		rj.setCollisionAllowed(false);
		this.world.addJoint(rj);
	}

	private void addDistJoint(RoThroJoint dist)
	{
		Obstacle anchor = dist.getBody1();
		Obstacle mass = dist.getBody2();

		DistanceJoint<SimulationBody> spring = new DistanceJoint<SimulationBody>(anchor, mass, dist.getAnchorPnt1(), dist.getAnchorPnt2());
		spring.setRestDistance(dist.getDistance());
		spring.setSpringDampingRatio(dist.getLinearDamping());
		spring.setSpringDamperEnabled(true);
		spring.setSpringFrequency(dist.getSpringFreq());
		spring.setSpringEnabled(true);
		spring.setMaximumSpringForce(dist.getMaxMotorForce());
		spring.setMaximumSpringForceEnabled(true);
		if (dist.getLimEn())
		{
			spring.setLimitsEnabled(dist.getLowerLimit(), dist.getUpperLimit());
		}

		this.world.addJoint(spring);
	}

	private void addPendJoint(RoThroJoint pend)
	{
		Obstacle support = pend.getBody1();
		Obstacle bob = pend.getBody2();
		Vector2 bobPos = bob.getWorldCenter();

		Vector2 supptPos = support.getWorldCenter();
		double rodLength = supptPos.y - bobPos.y;
		double rodY = rodLength / 2 + bobPos.y;
		
		Obstacle rod = new Obstacle(new Rectangle(0.5, rodLength), supptPos.x, rodY, new Color(200, 20, 20), true, "", "NORM", bob.getLevel());

		RevoluteJoint<SimulationBody> hinge1 = new RevoluteJoint<SimulationBody>(support, rod, supptPos);
		RevoluteJoint<SimulationBody> hinge2 = new RevoluteJoint<SimulationBody>(bob, rod, bobPos);	

		RevoluteJoint<SimulationBody> pendulum = new RevoluteJoint<SimulationBody>(support, bob, supptPos);
		pendulum.setCollisionAllowed(false);

		if (pend.getLimEn())
		{
			pendulum.setLimitsEnabled(pend.getLowerLimit(), pend.getUpperLimit());
		}

		FrictionJoint<SimulationBody> f = new FrictionJoint<SimulationBody>(support, bob, supptPos);
		f.setMaximumForce(0);
		f.setMaximumTorque(pend.getMaxMotorTorque());
		
		this.world.addBody(rod);
		this.world.addJoint(pendulum);
		this.world.addJoint(hinge1);
		this.world.addJoint(hinge2);
		this.world.addJoint(f);
	}

	/**
	 * Helper method for adding the whole to the right wall.
	 */
	private void addHole() {
		Hole hole = level.getHole();
		if (hole == null) {
			this.world.addBody(new WallObstacle(new Rectangle(1.0, height), width / 2, 0, false, 0));
			return;
		}

		int levelNum = level.getLevelNum();

		Obstacle upper = new WallObstacle(
			new Rectangle(1.0, hole.getY() - hole.getSize() / 2),
			width / 2,
			(height - hole.getY() + hole.getSize() / 2) / 2, false, levelNum
		);
		Obstacle lower = new WallObstacle(
			new Rectangle(1.0, height - hole.getY() - hole.getSize() / 2),
			width / 2,
			(-hole.getY() - hole.getSize() / 2) / 2, false, levelNum
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
		Vector2 ballCoords = ball.getWorldCenter();

		if (Math.abs(ballCoords.x) > width / 2 + ball.getRadius() || Math.abs(ballCoords.y) > height / 2 + ball.getRadius()) {
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
