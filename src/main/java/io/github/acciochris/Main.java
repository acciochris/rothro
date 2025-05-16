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
        level.addObstacle(new Obstacle(new Rectangle(1.0, 2.0), 5, -8.0));
        level.addObstacle(new Obstacle(new Rectangle(2.0, 1.0), 3.0, -5.0));
        level.addObstacle(new Obstacle(new Circle(2.0), 5.0, 10.0));
        level.addObstacle(new Obstacle(new Capsule(5.0, 2.0), 9.0, 6.0));
        level.addObstacle(new Obstacle(new Segment(new Vector2(-1.0, 4.0), new Vector2(5.0, 8.0)), 7.0, -9.0));
        level.setHole(new Hole(8.0, 3.0));
        return level;
    }

    public static Level level1()
    {
        return null; // FIX THIS!
    }

    public static Level level2()
    {
        return null; // FIX THIS!
    }

    public static void main(String[] args) {
        RoThro rothro = new RoThro(LEVELS[0]);
        rothro.run();
    }
}
