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
            (coords.x + Math.cos(Math.toRadians(angle)) * radius * 3.0) * scale,
            (coords.y + Math.sin(Math.toRadians(angle)) * radius * 3.0) * scale
        );

        g.setColor(new Color(0xF1FA8C));
        g.draw(aimLine);
    }
}
