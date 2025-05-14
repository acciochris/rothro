package io.github.acciochris;

import java.util.ArrayList;
import java.util.HashSet;
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

    public Ball()
    {
        angle = 90;
        addFixture(new Circle(1));
        setMass(MassType.NORMAL);
        setAngularDamping(1000.0);
        controls = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= 5; i++)
        {
            controls.add(new ArrayList<Integer>());
        }
        controls.get(0).add(KeyEvent.VK_LEFT);
        controls.get(1).add(KeyEvent.VK_RIGHT);
        controls.get(2).add(KeyEvent.VK_S);
    }
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
    public void controls(HashSet<Integer> keysPressed)
    {
        for (int i:keysPressed)
        {
            if(controls.get(0).contains(i))
            {
                System.out.println("hapy");
                angle += 0.5;
                System.out.println(angle);
            }
            if(controls.get(1).contains(i))
            {
                System.out.println("happens");
                angle -= 0.5;
                System.out.println(angle);
            }
            if(controls.get(2).contains(i))
            {
                setLinearVelocity(new Vector2(100*Math.cos(Math.toRadians(angle)), 100*Math.sin(Math.toRadians(angle))).multiply(0.30));
            }
        }
    }
}
