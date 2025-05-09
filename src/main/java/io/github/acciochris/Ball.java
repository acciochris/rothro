package io.github.acciochris;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
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
    private ArrayList<ArrayList<String>> controls;

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
        controls = new ArrayList<ArrayList<String>>(5);
    }
    public void setControls(String control)
    {
        
    }
    public void control()
    {
    }
}
