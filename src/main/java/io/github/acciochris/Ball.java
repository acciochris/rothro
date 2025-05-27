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
        controls = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= 5; i++)
        {
            controls.add(new ArrayList<Integer>());
        }
        controls.get(0).add(KeyEvent.VK_LEFT);
        controls.get(1).add(KeyEvent.VK_RIGHT);
        controls.get(2).add(KeyEvent.VK_S);
    }

    /**
     * Update the functionalities of key presses.
     * 
     * @param option what to do to the subject control
     * @param subjectControl the control in question to change
     * @param replacementControl the control to replace it with
     */
    public void setControls( char option, int subjectControl, int replacementControl)
    {
        if (option == 'd')
        {
            for (int a: controls.get(subjectControl))
            {
                controls.get(subjectControl).remove(a);
            }
        }
        if (option == 'a')
        {
            controls.get(subjectControl).add(replacementControl);
        }
    }

    /**
     * Handle all movement of the ball including turning and shooting.
     * 
     * @param keysPressed the HashSet of all currently depressed keys
     */
    public void controls(HashSet<Integer> keysPressed)
    {
        for (int i:keysPressed)
        {
            if(controls.get(0).contains(i))
            {
                angle += 1;
            }
            if(controls.get(1).contains(i))
            {
                angle -= 1;
            }
            if(controls.get(2).contains(i))
            {
                setLinearVelocity(new Vector2(40*Math.cos(Math.toRadians(angle)), 40*Math.sin(Math.toRadians(angle))).multiply(0.30));
            }
        }
    }

    public double getRadius() 
    { 
        return radius; 
    }

    /**
     * Render a line to show the direction the ball is to go.
     * 
     * @param g 2D graphics controller
     * @param scale scale of the window
     * @param color passed to superclass method
     */
    @Override
    public void render(Graphics2D g, double scale, Color color) {
        super.render(g, scale, color);

        Vector2 coords = getWorldCenter();
        Line2D.Double aimLine = new Line2D.Double(
            coords.x * scale,
            coords.y * scale,
            (coords.x + Math.cos(Math.toRadians(angle)) * radius * 3.0) * scale,
            (coords.y + Math.sin(Math.toRadians(angle)) * radius * 3.0) * scale
        );

        g.setColor(new Color(0xF1FA8C));
        g.draw(aimLine);
    }
}
