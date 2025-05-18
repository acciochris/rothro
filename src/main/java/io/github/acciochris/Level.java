package io.github.acciochris;

import java.util.LinkedList;
import java.util.List;
import org.dyn4j.dynamics.joint.Joint;
import org.dyn4j.geometry.Vector2;
import io.github.acciochris.framework.SimulationBody;

public class Level {
    private List<List<Obstacle>> obstacles;
    private List<Integer> joints; // The integers refer to the list in the Obstacle list that contain movable objects
    private Hole hole;
    private Vector2 ballPos;
    private double ballRadius;

    public Level() {
        this(new LinkedList<>(), null, null);
    }

    public Level(List<List<Obstacle>> obstacles, Hole hole, List<Integer> joints) {
        this.obstacles = obstacles;
        this.joints = joints;
        this.hole = hole;
    }

    public void addObstacle(List<Obstacle> obstacle) {
        obstacles.add(obstacle);
    }

    public List<List<Obstacle>> getObstacles() {
        return obstacles;
    }

    public Hole getHole()
    {
        return hole;
    }

    public void setBallPos(Vector2 ballPos)
    {
        this.ballPos = ballPos;
    }

    public Vector2 getBallPos()
    {
        return ballPos;
    }

    public void setBallRadius(double ballRadius)
    {
        this.ballRadius = ballRadius;
    }

    public double getBallRadius()
    {
        return ballRadius;
    }

    public void setHole(Hole hole)
    {
        this.hole = hole;
    }

    public boolean hasJoints()
    {
        return joints != null && joints.size() > 0;
    }

    public List<Integer> getJoints()
    {
        return joints;
    }

    public void setJoints(List<Integer> joints)
    {
        this.joints = joints;
    }

    public void addJoint(Integer joint)
    {
        joints.add(joint);
    }

}
