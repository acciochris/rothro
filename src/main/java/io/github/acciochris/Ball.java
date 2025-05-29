package io.github.acciochris;

import java.awt.Color;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Circle;
import io.github.acciochris.framework.SimulationBody;

/**
 * Represents the ball in the RoThro game.
 * 
 * @author Chris Liu
 * @author Justin Huang
 * @author Anvesh Pattaje
 * @version May 30, 2025
 */
public class Ball
    extends SimulationBody
{
    private double radius;

    /**
     * Construct a new ball with radius r.
     * 
     * @param r
     *            ball radius
     */
    public Ball(double r)
    {
        super(new Color(0xFF79C6));
        this.radius = r;
        addFixture(new Circle(r));
        setMass(MassType.NORMAL);
        setAngularDamping(1000.0);
        setLinearDamping(0.1);
        setAtRestDetectionEnabled(false);
    }


    /**
     * Getter for ball radius.
     * 
     * @return ball radius
     */
    public double getRadius()
    {
        return radius;
    }
}
