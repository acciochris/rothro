package io.github.acciochris;

import org.dyn4j.geometry.Vector2;

/**
 * This class stores the details required to create a joint in the RoThro game.
 * 
 * @author Anvesh Pattaje
 * @version May 30, 2025
 */
public class RoThroJoint 
{
    private String type;
    private double distance;
    private double maxMotorForce;
    private double maxMotorTorque;
    private double motorSpeed;
    private double springFreq;
    private double angularDamping;
    private double lowerLimit;
    private double upperLimit;
    private double body1Speed;
    private double body2Speed;
    private double linearDamping;
    private Vector2 axis;
    private Vector2 anchorPnt1;
    private Vector2 anchorPnt2;
    private Obstacle body1;
    private Obstacle body2;
    private boolean limitsEnabled;

    /**
     * Construct a new RoThro joint based on various parameters.
     * 
     * @param t the type of Joint
     * @param dist desired target distance if required by a Joint
     * @param mMotF maximum motor force that an enabled motor can exert to make the body involved in the Joint reach motorSpeed
     * @param mMotT maximum motor torque that an enabled motor can exert to make the body involved in the Joint reach a desired angular velocity
     * @param speed desired motor speed
     * @param f frequency of SHM
     * @param axis the axis along which a Body will move; used in Prismatic Joints
     * @param b1 Obstacle 1
     * @param b2 Obstacle 2
     */
    public RoThroJoint(String t, double linDampg, double b1Speed, double b2Speed, double upLim, double lowLim, double angDampg, double dist, double mMotF, double mMotT, double speed, double f, Vector2 axis, Vector2 anchorPnt1, Vector2 anchorPnt2, Obstacle b1, Obstacle b2, boolean limEn)
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
        limitsEnabled = limEn;
    }

    /**
     * Getter for axis.
     * 
     * @return axis
     */
    public Vector2 getAxis()
    {
        return axis;
    }

    /**
     * Getter for body1.
     * 
     * @return body1
     */
    public Obstacle getBody1()
    {
        return body1;
    }

    /**
     * Getter for body2.
     * 
     * @return body2
     */
    public Obstacle getBody2()
    {
        return body2;
    }

    /**
     * Getter for distance.
     * 
     * @return distance
     */
    public double getDistance()
    {
        return distance;
    }

    /**
     * Getter for maxMotorForce.
     * 
     * @return maxMotorForce
     */
    public double getMaxMotorForce()
    {
        return maxMotorForce;
    }

    /**
     * Getter for maxMotorTorque.
     * 
     * @return maxMotorTorque
     */
    public double getMaxMotorTorque()
    {
        return maxMotorTorque;
    }

    /**
     * Getter for motorSpeed.
     * 
     * @return motorSpeed
     */
    public double getMotorSpeed()
    {
        return motorSpeed;
    }

    /**
     * Getter for anchorPnt1.
     * 
     * @return anchorPnt1
     */
    public Vector2 getAnchorPnt1()
    {
        return anchorPnt1;
    }

    /**
     * Getter for linearDamping.
     * 
     * @return linearDamping
     */
    public double getLinearDamping()
    {
        return linearDamping;
    }

    /**
     * Getter for springFreq.
     * 
     * @return springFreq
     */
    public double getSpringFreq()
    {
        return springFreq;
    }

    /**
     * Getter for whether limits are enabled.
     * 
     * @return limitsEnabled
     */
    public boolean getLimEn()
    {
        return limitsEnabled;
    }

    /**
     * Getter for joint type.
     * 
     * @return joint type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Getter for body1Speed.
     * 
     * @return body1Speed
     */
    public double getBody1Speed()
    {
        return body1Speed;
    }

    /**
     * Getter for body2Speed.
     * 
     * @return body2Speed
     */
    public double getBody2Speed()
    {
        return body2Speed;
    }

    /**
     * Getter for anchorPnt2.
     * 
     * @return anchorPnt2
     */
    public Vector2 getAnchorPnt2()
    {
        return anchorPnt2;
    }

    /**
     * Getter for angularDamping.
     * 
     * @return angularDamping
     */
    public double getAngularDamping()
    {
        return angularDamping;
    }

    /**
     * Getter for lowerLimit.
     * 
     * @return lowerLimit
     */
    public double getLowerLimit()
    {
        return lowerLimit;
    }

    /**
     * Getter for upperLimit.
     * 
     * @return upperLimit
     */
    public double getUpperLimit()
    {
        return upperLimit;
    }

    /**
     * Setter for axis.
     * 
     * @param axis new axis
     */
    public void setAxis(Vector2 axis)
    {
        this.axis = axis;
    }

    /**
     * Setter for limitsEnabled.
     * 
     * @param limitsEnabled new limitsEnabled
     */
    public void setLimitsEnabled(boolean limitsEnabled)
    {
        this.limitsEnabled = limitsEnabled;
    }

    /**
     * Setter for body1Speed.
     * 
     * @param body1Speed new body1Speed
     */
    public void setBody1Speed(double body1Speed)
    {
        this.body1Speed = body1Speed;
    }

    /**
     * Setter for body2Speed.
     * 
     * @param body2Speed new body2Speed
     */
    public void setBody2Speed(double body2Speed)
    {
        this.body2Speed = body2Speed;
    }

    /**
     * Setter for anchorPnt1.
     * 
     * @param anchorPnt new anchorPnt1
     */
    public void setAnchorPnt1(Vector2 anchorPnt)
    {
        this.anchorPnt1 = anchorPnt;
    }

    /**
     * Setter for anchorPnt2.
     * 
     * @param anchorPnt2 new anchorPnt2
     */
    public void setAnchorPnt2(Vector2 anchorPnt2)
    {
        this.anchorPnt2 = anchorPnt2;
    }

    /**
     * Setter for angularDamping.
     * 
     * @param angularDamping new angularDamping
     */
    public void setAngularDamping(double angularDamping)
    {
        this.angularDamping = angularDamping;
    }

    /**
     * Setter for linearDamping.
     * 
     * @param linearDamping new linearDamping
     */
    public void setLinearDamping(double linearDamping)
    {
        this.linearDamping = linearDamping;
    }

    /**
     * Setter for lowerLimit.
     * 
     * @param lowerLimit new lowerLimit
     */
    public void setLowerLimit(double lowerLimit)
    {
        this.lowerLimit = lowerLimit;
    }

    /**
     * Setter for upperLimit.
     * 
     * @param upperLimit new upperLimit
     */
    public void setUpperLimit(double upperLimit)
    {
        this.upperLimit = upperLimit;
    }

    /**
     * Setter for body1.
     * 
     * @param body1 new body1
     */
    public void setBody1(Obstacle body1)
    {
        this.body1 = body1;
    }

    /**
     * Setter for body2.
     * 
     * @param body2 new body2
     */
    public void setBody2(Obstacle body2)
    {
        this.body2 = body2;
    }

    /**
     * Setter for distance.
     * 
     * @param distance new distance
     */
    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    /**
     * Setter for maxMotorForce.
     * 
     * @param maxMotorForce new MaxMotorForce
     */
    public void setMaxMotorForce(double maxMotorForce)
    {
        this.maxMotorForce = maxMotorForce;
    }

    /**
     * Setter for maxMotorTorque.
     * 
     * @param maxMotorTorque new maxMotorTorque
     */
    public void setMaxMotorTorque(double maxMotorTorque)
    {
        this.maxMotorTorque = maxMotorTorque;
    }

    /**
     * Setter for motorSpeed.
     * 
     * @param motorSpeed new motorSpeed
     */
    public void setMotorSpeed(double motorSpeed)
    {
        this.motorSpeed = motorSpeed;
    }

    /**
     * Setter for springFreq.
     * 
     * @param springFreq new springFreq
     */
    public void setSpringFreq(double springFreq)
    {
        this.springFreq = springFreq;
    }

    /**
     * Setter for joint type
     * 
     * @param type new joint type
     */
    public void setType(String type)
    {
        this.type = type;
    }
}
