package io.github.acciochris;

import org.dyn4j.geometry.*;

public class Main {
    public static final Level[] LEVELS = {
        level0()
    };

    public static Level level0()
    {
        Level level = new Level();
        level.addObstacle(new Obstacle(new Rectangle(2.0, 1.0), 3.0, 3.0));
        level.addObstacle(new Obstacle(new Circle(4.0), 7.0, 6.0));
        return level;
    }

    public static void main(String[] args) {
        RoThro rothro = new RoThro(LEVELS[0]);
        rothro.run();
    }
}
