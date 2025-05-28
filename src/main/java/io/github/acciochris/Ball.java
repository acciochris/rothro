package io.github.acciochris;

import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.event.KeyEvent;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Vector2;
import io.github.acciochris.framework.SimulationBody;

/**
 * Represens the ball in the RoThro game.
 * 
 * @author Chris Liu
 * @author Justin Huang
 * @author Anvesh Pattaje
 * @version May 30, 2025
 */
public class Ball extends SimulationBody
{
    private double angle;
    private double radius;
    private ArrayList<ArrayList<Integer>> controls;

    /**
     * Construct a new ball with radius r.
     * 
     * @param r ball radius
     */
    public Ball(double r)
    {
        super(new Color(0xFF79C6));
        angle = 90;
        this.radius = r;
        addFixture(new Circle(r));
        setMass(MassType.NORMAL);
        setAngularDamping(1000.0);
        setLinearDamping(0.1);
        setAtRestDetectionEnabled(false);
    }

    public double getRadius() 
    { 
        return radius; 
    }
}
