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
    // private double velx;
    // private double vely;
    public static final Color DEFAULT_COLOR = new Color(0x6272a4);

    public Obstacle(Convex shape, double x, double y, Color color, boolean canMove, String jT) {
        super(color);
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.movable = canMove;
        this.jointType = jT;

        if (!movable)
        {
            BodyFixture fixture = addFixture(shape, 1.0, 0.0, 0.8);
            fixture.setRestitutionVelocity(0.0);
            setMass(MassType.INFINITE);
        }

        else
        {
            BodyFixture fixture = addFixture(shape, 0.8, 0.0, 0.75);
            fixture.setRestitutionVelocity(0.5);
            setMass(MassType.NORMAL);
        }
        translate(x, y);
        setAtRestDetectionEnabled(false);
    }

    public Obstacle(Convex shape, double x, double y, boolean canMove) {
        this(shape, x, y, DEFAULT_COLOR, canMove, null);
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

    // public void move()
    // {
    //     translate(velx, vely);
    // }
}
