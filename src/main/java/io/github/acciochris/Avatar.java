package io.github.acciochris;

import java.util.ArrayList;
import java.util.HashSet;
import javax.naming.spi.ResolveResult;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.dyn4j.collision.Filter;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.PhysicsBody;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;
import io.github.acciochris.framework.SimulationBody;
import io.github.acciochris.framework.SimulationFrame;
import io.github.acciochris.framework.input.BooleanStateKeyboardInputHandler;
import org.dyn4j.dynamics.joint.RevoluteJoint;
public class Avatar extends SimulationBody
{
    private RevoluteJoint<SimulationBody> armJoint1;
    private RevoluteJoint<SimulationBody> armJoint2;
    public Rectangle body;
    private ArrayList<ArrayList<Integer>> controls;
    private double armAngle1;
    private double armAngle2;
    private RevoluteJoint<SimulationBody> selectedLimb;

    public Avatar(RevoluteJoint<SimulationBody> armJoint1, RevoluteJoint<SimulationBody> armJoint2)
    {
        armAngle1 = 0;
        armAngle2 = 180;
        body = new Rectangle(3,5);
        addFixture(body);
        this.armJoint1 = armJoint1;
        this.armJoint2 = armJoint2;
        controls = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= 5; i++)
        {
            controls.add(new ArrayList<Integer>());
        }
        controls.get(0).add(KeyEvent.VK_LEFT);
        controls.get(1).add(KeyEvent.VK_RIGHT);
    }

    public void controls(HashSet<Integer> keysPressed)
    {
        armAngle1 = armJoint1.getBody2().getTransform().getRotationAngle();
        armAngle2 = armJoint2.getBody2().getTransform().getRotationAngle();
        for (int i:keysPressed)
        {
            switch(i)
            {
                case KeyEvent.VK_1:
                selectedLimb = armJoint1;
                case KeyEvent.VK_2:
                selectedLimb = armJoint2;
            }

            /*
             * Controls Utilization of Arms
             */
            if(controls.get(0).contains(i))
            {
                if (armAngle1 <= 90)
                {
                    armJoint1.getBody2().setAngularVelocity(1);
                }
                if (armAngle2 <= 270)
                {
                    armJoint2.getBody2().setAngularVelocity(1);
                }
            }
            if(controls.get(1).contains(i))
            {
                if (armAngle1 >= -90)
                {
                    armJoint1.getBody2().setAngularVelocity(-1);
                }
                if (armAngle2 >= 90)
                {
                    armJoint2.getBody2().setAngularVelocity(-1);
                }
            }
            if (armAngle1 <= 90 || armAngle1 >= -90)
            {
                armJoint1.getBody2().setAngularVelocity(0);
            }
            if (armAngle2 <= 270 || armAngle2 >= 90)
            {
                armJoint2.getBody2().setAngularVelocity(0);
            }
            if(controls.get(2).contains(i))
            {
                if (selectedLimb == armJoint1)
                {
                    ((Arm)selectedLimb.getBody2()).shoot();
                }
                if (selectedLimb == armJoint2)
                {
                    ((Arm)selectedLimb.getBody2()).shoot();
                }
            }

            /*
             * Controls Movement with Legs
             */
            if(controls.get(3).contains(i))
            {

            }
            if(controls.get(4).contains(i))
            {

            }
        }
    }

        /*
     * Being able to attach and detach limbs at will is a luxury at the moment may be implemented in the future
     */
    // public void Attachment(SimulationBody a)
    // {
    //     if (a instanceof Arm && (armJoint1.equals(null) || armJoint2.equals(null)))
    //     {
    //         if (armJoint1.equals(null))
    //         {
    //             armJoint1 = new RevoluteJoint<>(this, a, new Vector2(body.getWidth()/2 + body.getX(), body.getY()));
    //         }
    //         else if (armJoint2.equals(null))
    //         {
    //             armJoint2 = new RevoluteJoint<>(this, a, new Vector2(body.getWidth()/2 - body.getX(), body.getY()));
    //         }
    //     }
    //     else return;
    // }
}
