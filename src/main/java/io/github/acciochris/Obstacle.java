package io.github.acciochris;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;
import java.awt.Color;
import java.awt.Graphics2D;
import io.github.acciochris.framework.SimulationBody;

/**
 * Represents an obstacle in a level. Extends SimulationBody.
 * 
 * @author Chris Liu
 * @author Anvesh Pattaje
 * @version May 30, 2025
 */
public class Obstacle
    extends SimulationBody
{
    private double            x;
    private double            y;
    private boolean           movable;
    private String            jointType;
    private Convex            shape;
    private String            massType;
    private int               level;
    protected boolean         visible;

    /**
     * Default color for obstacles.
     */
    public static final Color DEFAULT_COLOR = new Color(0x6272a4);

    /**
     * Construct a new Obstacle from various parameters.
     * 
     * @param shape
     *            a Convex shape for the obstacle
     * @param x
     *            x coordinate of the obstacle
     * @param y
     *            y coordinate of the obstacle
     * @param color
     *            color of the obstacle
     * @param canMove
     *            boolean parameter for whether the obstacle can move
     * @param jT
     *            joint type for this obstacle
     * @param mT
     *            mass type for this obstacle
     */
    public Obstacle(
        Convex shape,
        double x,
        double y,
        Color color,
        boolean canMove,
        String jT,
        String mT,
        int level)
    {
        super(color);
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.movable = canMove;
        this.jointType = jT;
        this.massType = mT;
        this.level = level;
        this.visible = true;

        if (!movable)
        {
            BodyFixture fixture = addFixture(shape, 1.0, 0.0, 0.8);
            fixture.setRestitutionVelocity(0.0);
            setMass(MassType.INFINITE);
        }

        else
        {
            BodyFixture fixture = null;
            if (massType.equals("FIXANG"))
            {
                fixture = addFixture(shape, 1.0, 0.0, 1);
                setMass(MassType.FIXED_ANGULAR_VELOCITY);
            }

            else if (massType.equals("NORM"))
            {
                fixture = addFixture(shape, 1.0, 0.0, 0.75);
                setMass(MassType.NORMAL);
            }

            fixture.setRestitutionVelocity(0.5);
        }

        translate(x, y);
        setAtRestDetectionEnabled(false);
    }


    /**
     * Simplified constructor for obstacles without colors or joints.
     * 
     * @param shape
     *            a Convex shape for the obstacle
     * @param x
     *            x coordinate of the obstacle
     * @param y
     *            y coordinate of the obstacle
     * @param canMove
     *            boolean parameter for whether the obstacle can move
     */
    public Obstacle(
        Convex shape,
        double x,
        double y,
        boolean canMove,
        int level)
    {
        this(shape, x, y, DEFAULT_COLOR, canMove, null, "", level);
    }


    /**
     * Getter for x coordinate.
     * 
     * @return x coordinate
     */
    public double getX()
    {
        return x;
    }


    /**
     * Getter for y coordinate.
     * 
     * @return y coordinate
     */
    public double getY()
    {
        return y;
    }


    /**
     * Getter for joint type.
     * 
     * @return joint type
     */
    public String getJointType()
    {
        return jointType;
    }


    /**
     * Getter for shape.
     * 
     * @return Convex shape
     */
    public Convex getShape()
    {
        return shape;
    }


    /**
     * Getter for mass type.
     * 
     * @return mass type
     */
    public String getMassType()
    {
        return massType;
    }


    /**
     * Getter for level.
     * 
     * @return level in which this obstacle is found
     */
    public int getLevel()
    {
        return level;
    }


    /**
     * Setter for visible.
     * 
     * @param visible
     *            visibility
     */
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }


    /**
     * Getter for visible.
     * 
     * @return true if the obstacle is visible
     */
    public boolean isVisible()
    {
        return visible;
    }


    /**
     * Render the obstacle if it's visible.
     * 
     * @param g
     *            the graphics object to render to
     * @param scale
     *            the scaling factor
     * @param color
     *            the color to render the body
     */
    @Override
    public void render(Graphics2D g, double scale, Color color)
    {
        if (visible)
        {
            super.render(g, scale, color);
        }
    }
}
