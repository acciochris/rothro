package io.github.acciochris;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;
import java.awt.Color;
import io.github.acciochris.framework.SimulationBody;


public class Obstacle extends SimulationBody 
{
    private double x;
    private double y;
    // private double velx;
    // private double vely;
    public static final Color DEFAULT_COLOR = new Color(0x6272a4);

    public Obstacle(Convex shape, double x, double y, Color color) {
        super(color);
        this.x = x;
        this.y = y;

        BodyFixture fixture = addFixture(shape, 1.0, 0.0, 0.8);
        fixture.setRestitutionVelocity(0.0);
        translate(x, y);
        setMass(MassType.INFINITE);
        setAtRestDetectionEnabled(false);
    }

    public Obstacle(Convex shape, double x, double y) {
        this(shape, x, y, DEFAULT_COLOR);
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    // public void move()
    // {
    //     translate(velx, vely);
    // }
}
