package io.github.acciochris;

import java.util.LinkedList;
import java.util.List;
import org.dyn4j.geometry.Vector2;

public class Level {
    private List<Obstacle> obstacles;
    private Hole hole;
    private Vector2 ballPos;
    private double ballRadius;

    public Level() {
        this(new LinkedList<>(), null);
    }

    public Level(List<Obstacle> obstacles, Hole hole) {
        this.obstacles = obstacles;
        this.hole = hole;
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public List<Obstacle> getObstacles() {
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
}
