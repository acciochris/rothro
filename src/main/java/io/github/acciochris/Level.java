package io.github.acciochris;

import java.util.LinkedList;
import java.util.List;

public class Level {
    private List<Obstacle> obstacles;

    public Level() {
        obstacles = new LinkedList<>();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
