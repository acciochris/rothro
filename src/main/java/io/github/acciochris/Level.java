package io.github.acciochris;

import java.util.LinkedList;
import java.util.List;
import org.dyn4j.geometry.Vector2;

/**
 * Level definition class. This defines the obstacles, the hole position, the
 * initial ball position, the ball radius, as well as any relevant joints.
 * 
 * @author Chris Liu
 * @author Anvesh Pattaje
 * @version May 30, 2025
 */
public class Level
{
    private List<List<Obstacle>> obstacles;
    private List<RoThroJoint>    joints;
    private Hole                 hole;
    private Vector2              ballPos;
    private double               ballRadius;
    private int                  levelNo;

    /**
     * Default constructor. Initializes everything to empty lists and/or null.
     */
    public Level()
    {
        this(new LinkedList<>(), null, null, 0);
    }


    /**
     * Constructor that sets the obstacles, the holes, and a list of joints.
     * 
     * @param obstacles
     *            a List of List of obstacles
     * @param hole
     *            the hole
     * @param joints
     *            a List of indices specifying which sublist of obstacles need a
     *            joint
     */
    public Level(
        List<List<Obstacle>> obstacles,
        Hole hole,
        List<RoThroJoint> joints,
        int num)
    {
        this.obstacles = obstacles;
        this.joints = joints;
        this.hole = hole;
        this.levelNo = num;
    }


    /**
     * Add a new set of obstacles (in a List).
     * 
     * @param obstacle
     *            the new List of obstacles to add
     */
    public void addObstacle(List<Obstacle> obstacle)
    {
        obstacles.add(obstacle);
    }


    /**
     * Getter for obstacles.
     * 
     * @return obstacles
     */
    public List<List<Obstacle>> getObstacles()
    {
        return obstacles;
    }


    /**
     * Getter for hole.
     * 
     * @return the hole
     */
    public Hole getHole()
    {
        return hole;
    }


    /**
     * Setter for initial ball position.
     * 
     * @param ballPos
     *            ball position as a vector
     */
    public void setBallPos(Vector2 ballPos)
    {
        this.ballPos = ballPos;
    }


    /**
     * Getter for initial ball position.
     * 
     * @return initial ball position
     */
    public Vector2 getBallPos()
    {
        return ballPos;
    }


    /**
     * Setter for ball radius
     * 
     * @param ballRadius
     *            ball radius
     */
    public void setBallRadius(double ballRadius)
    {
        this.ballRadius = ballRadius;
    }


    /**
     * Getter for ball radius
     * 
     * @return ball radius
     */
    public double getBallRadius()
    {
        return ballRadius;
    }


    /**
     * Setter for hole
     * 
     * @param hole
     *            the hole
     */
    public void setHole(Hole hole)
    {
        this.hole = hole;
    }


    /**
     * Returns whether there are any joints
     * 
     * @return true if the joints list isn't empty
     */
    public boolean hasJoints()
    {
        return joints != null && joints.size() > 0;
    }


    /**
     * Getter for joints
     * 
     * @return joints
     */
    public List<RoThroJoint> getJoints()
    {
        return joints;
    }


    /**
     * Setter for joints
     * 
     * @param joints
     *            joints
     */
    public void setJoints(List<RoThroJoint> joints)
    {
        this.joints = joints;
    }


    /**
     * Add a new joint to the list of joints
     * 
     * @param joint
     *            the new joint to add
     */
    public void addJoint(RoThroJoint joint)
    {
        joints.add(joint);
    }


    /**
     * Getter for level number
     * 
     * @return level number
     */
    public int getLevelNum()
    {
        return levelNo;
    }


    /**
     * Setter for level number
     * 
     * @param levelNo
     *            level number
     */
    public void setLevelNum(int levelNo)
    {
        this.levelNo = levelNo;
    }
}
