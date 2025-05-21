package io.github.acciochris;

import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import java.util.Vector;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.WeldJoint;
import io.github.acciochris.framework.SimulationBody;

public class Arm extends SimulationBody
{
    private Rectangle arm;
    private Ball ball;
    private WeldJoint hand;
    private Vector2 armAvatarPoint;
    private Vector2 armBallPoint;

    public Arm(Vector2 point)
    {
        arm = new Rectangle(5, 1);

        armAvatarPoint = this.getLocalPoint(point);

        armBallPoint = new Vector2(-armAvatarPoint.x, armAvatarPoint.y);
    }

    public void attachment(SimulationBody a)
    {
        if (a instanceof Ball && hand.equals(null))
        {
            hand = new WeldJoint<SimulationBody>(this, a, armBallPoint);
        }
        else return;
    }

    public Vector2 shoot()
    {
        if (ball != null)
        {
            Vector2 bottomRight = this.getWorldPoint(new Vector2(arm.getWidth()/2, -arm.getHeight()/2));
            Vector2 bottomLeft = this.getWorldPoint(new Vector2(-arm.getWidth()/2, -arm.getHeight()/2));
            double angle = Math.atan2(Math.toRadians(bottomRight.y - bottomLeft.y), Math.toRadians(bottomRight.x - bottomLeft.x)) + Math.PI/2;
            hand.getBody2().setLinearVelocity(Math.cos(angle), Math.sin(angle));
            return new Vector2(0,0);
        }
        else
        {
            return armBallPoint;
        }
    }
}