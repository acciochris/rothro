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
import io.github.acciochris.framework.Camera;
import io.github.acciochris.framework.SimulationBody;
import io.github.acciochris.framework.SimulationFrame;
import io.github.acciochris.framework.input.BooleanStateKeyboardInputHandler;
import io.github.acciochris.framework.input.Key;
import org.dyn4j.world.World;

public class Ball extends SimulationBody
{
    private ArrayList<ArrayList<Integer>> controls;

    private BooleanStateKeyboardInputHandler left;
	private BooleanStateKeyboardInputHandler right;
	
	private BooleanStateKeyboardInputHandler plus;
	private BooleanStateKeyboardInputHandler minus;
	
	private BooleanStateKeyboardInputHandler shoot;

    public Ball() 
    {
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
        controls.get(2).add(KeyEvent.VK_SPACE);
    }
    public void setControls(String control)
    {
        
    }
    public void controls(HashSet<Integer> keysPressed)
    {
        int angle = 90;
        for (int i:keysPressed)
        {
            while(controls.get(0).contains(i))
            {
                angle += 0.005;
            }
            while(controls.get(1).contains(i))
            {
                angle -= 0.005;
            }
            if(controls.get(2).contains(i))
            {
                setLinearVelocity(new Vector2(Math.cos(angle), Math.sin(angle)));
            }
        }
    }
}
