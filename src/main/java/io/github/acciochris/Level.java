package io.github.acciochris;

import java.util.LinkedList;
import java.util.List;
import io.github.acciochris.Obstacle;

public class Level {
    private List<Obstacle> obstacles;

    public Level() {
        obstacles = new LinkedList<>();
    }

    public void addObstacle(Obstacle obstacle) {

    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
