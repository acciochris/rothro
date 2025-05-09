package io.github.acciochris;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;
import io.github.acciochris.framework.SimulationBody;


public class Obstacle extends SimulationBody 
{
    private double x;
    private double y;
    // private double velx;
    // private double vely;

    public Obstacle(Convex shape, double x, double y) {
        this.x = x;
        this.y = y;

        BodyFixture fixture = addFixture(shape, 1.0, 0.0, 0.8);
        fixture.setRestitutionVelocity(0.0);
        translate(x, y);
        setMass(MassType.INFINITE);
        setAtRestDetectionEnabled(false);
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
