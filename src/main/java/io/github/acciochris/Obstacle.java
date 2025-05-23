package io.github.acciochris;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;
import java.awt.Color;
import java.awt.Point;
import io.github.acciochris.framework.SimulationBody;


public class Obstacle extends SimulationBody 
{
    private double x;
    private double y;
    private boolean movable;
    private String jointType;
    private Convex shape;
    private String massType;
    public static final Color DEFAULT_COLOR = new Color(0x6272a4);

    public Obstacle(Convex shape, double x, double y, Color color, boolean canMove, String jT, String mT) {
        super(color);
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.movable = canMove;
        this.jointType = jT;
        this.massType = mT;

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

    public Obstacle(Convex shape, double x, double y, boolean canMove) {
        this(shape, x, y, DEFAULT_COLOR, canMove, "", "");
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public String getJointType()
    {
        return jointType;
    }

    public Convex getShape()
    {
        return shape;
    }

    public String getMassType()
    {
        return massType;
    }
}
