package io.github.acciochris;

import org.dyn4j.geometry.*;
import io.github.acciochris.framework.SimulationBody;


public class Obstacle extends SimulationBody 
{
    private double x;
    private double y;

    public Obstacle(Convex shape, double x, double y) {
        addFixture(shape);
        translate(x, y);
        this.x = x;
        this.y = y;
        setMass(MassType.INFINITE);
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}
