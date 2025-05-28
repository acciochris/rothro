package io.github.acciochris;

import org.dyn4j.geometry.Vector2;

/**
 * This class stores the details required to create a joint in the RoThro game
 * @author Anvesh Pattaje
 * @version May 30, 2025
 */
public class RoThroJoint 
{
    String type;
    double distance;
    double maxMotorForce;
    double maxMotorTorque;
    double motorSpeed;
    double springConst;
    double springFreq;
    double angularDamping;
    double lowerLimit;
    double upperLimit;
    double body1Speed;
    double body2Speed;
    double linearDamping;
    Vector2 axis;
    Vector2 anchorPnt1;
    Vector2 anchorPnt2;
    Obstacle body1;
    Obstacle body2;

    /**
     * @param t - the type of Joint
     * @param dist - desired target distance if required by a Joint
     * @param mMotF - maximum motor force that an enabled motor can exert to make the body involved in the Joint reach motorSpeed
     * @param mMotT - maximum motor torque that an enabled motor can exert to make the body involved in the Joint reach a desired angular velocity
     * @param speed - desired motor speed
     * @param f - frequency of SHM
     * @param axis - the axis along which a Body will move; used in Prismatic Joints
     * @param b1 - Obstacle 1
     * @param b2 - Obstacle 2
     */
    public RoThroJoint(String t, double linDampg, double b1Speed, double b2Speed, double upLim, double lowLim, double angDampg, double dist, double mMotF, double mMotT, double speed, double f, Vector2 axis, Vector2 anchorPnt1, Vector2 anchorPnt2, Obstacle b1, Obstacle b2)
    {
        type = t;
        distance = dist;
        maxMotorForce = mMotF;
        motorSpeed = speed;
        springFreq = f;
        body1 = b1;
        body2 = b2;
        this.axis = axis;
        this.anchorPnt1 = anchorPnt1;
        this.anchorPnt2 = anchorPnt2;
        maxMotorTorque = mMotT;
        lowerLimit = lowLim;
        upperLimit = upLim;
        angularDamping = angDampg;
        body1Speed = b1Speed;
        body2Speed = b2Speed;
        linearDamping = linDampg;
    }

    public Vector2 getAxis()
    {
        return axis;
    }

    public Obstacle getBody1()
    {
        return body1;
    }

    public Obstacle getBody2()
    {
        return body2;
    }
    public double getDistance()
    {
        return distance;
    }

    public double getMaxMotorForce()
    {
        return maxMotorForce;
    }

    public double getMaxMotorTorque()
    {
        return maxMotorTorque;
    }

    public double getMotorSpeed()
    {
        return motorSpeed;
    }

    public Vector2 getAnchorPnt1()
    {
        return anchorPnt1;
    }

    public double getLinearDamping()
    {
        return linearDamping;
    }

    public double getSpringFreq()
    {
        return springFreq;
    }

    public String getType()
    {
        return type;
    }

    public double getBody1Speed()
    {
        return body1Speed;
    }

    public double getBody2Speed()
    {
        return body2Speed;
    }

    public Vector2 getAnchorPnt2()
    {
        return anchorPnt2;
    }

    public double getAngularDamping()
    {
        return angularDamping;
    }

    public double getLowerLimit()
    {
        return lowerLimit;
    }

    public double getUpperLimit()
    {
        return upperLimit;
    }

    public void setAxis(Vector2 axis)
    {
        this.axis = axis;
    }

    public void setBody1Speed(double body1Speed)
    {
        this.body1Speed = body1Speed;
    }

    public void setBody2Speed(double body2Speed)
    {
        this.body2Speed = body2Speed;
    }

    public void setAnchorPnt1(Vector2 anchorPnt)
    {
        this.anchorPnt1 = anchorPnt;
    }

    public void setAnchorPnt2(Vector2 anchorPnt2)
    {
        this.anchorPnt2 = anchorPnt2;
    }

    public void setAngularDamping(double angularDamping)
    {
        this.angularDamping = angularDamping;
    }

    public void setLinearDamping(double linearDamping)
    {
        this.linearDamping = linearDamping;
    }

    public void setLowerLimit(double lowerLimit)
    {
        this.lowerLimit = lowerLimit;
    }

    public void setUpperLimit(double upperLimit)
    {
        this.upperLimit = upperLimit;
    }

    public void setBody1(Obstacle body1)
    {
        this.body1 = body1;
    }

    public void setBody2(Obstacle body2)
    {
        this.body2 = body2;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public void setMaxMotorForce(double maxMotorForce)
    {
        this.maxMotorForce = maxMotorForce;
    }

    public void setMaxMotorTorque(double maxMotorTorque)
    {
        this.maxMotorTorque = maxMotorTorque;
    }

    public void setMotorSpeed(double motorSpeed)
    {
        this.motorSpeed = motorSpeed;
    }

    public void setSpringFreq(double springFreq)
    {
        this.springFreq = springFreq;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
