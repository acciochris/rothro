package io.github.acciochris;

import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.dyn4j.collision.Filter;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;
import io.github.acciochris.framework.SimulationBody;
import io.github.acciochris.framework.input.BooleanStateKeyboardInputHandler;

public class Ball extends SimulationBody
{
    public double angle;
    
    private ArrayList<ArrayList<Integer>> controls;
	
	private BooleanStateKeyboardInputHandler shoot;

    private double radius;

    public Ball(double r)
    {
        super(new Color(0xff79C6));
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
     * Calling this function results in a logic chain that allows the user to change what certain keys do
     * @param option - what to do to the subject control
     * @param subjectControl - the control in question to change
     * @param replacementControl - the control to replace it with
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
     * Handles all movement of the ball including turning and shooting
     * @param keysPressed - the hash set of all the keys pressed at the current cycle of the game loop
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
     * Renders a line to show the direction the ball is to go in when the shoot button is pressed
     * @param g - controls the graphical interface for the line
     * @param scale - controls the length of the line
     * @param color - color of the line
     */
    @Override
    public void render(Graphics2D g, double scale, Color color) {
        super.render(g, scale, color);

        Vector2 coords = getWorldCenter();
        Line2D.Double aimLine = new Line2D.Double(
            coords.x * scale,
            coords.y * scale,
            (coords.x + Math.cos(Math.toRadians(angle)) * 3.0) * scale,
            (coords.y + Math.sin(Math.toRadians(angle)) * 3.0) * scale
        );

        g.setColor(new Color(0xF1FA8C));
        g.draw(aimLine);
    }
}
