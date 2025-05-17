package io.github.acciochris;

import java.awt.Point;
import org.dyn4j.dynamics.joint.RevoluteJoint;
import org.dyn4j.geometry.*;
import io.github.acciochris.framework.SimulationBody;

public class Main {
    public static final Level[] LEVELS = {
        level0(),
        level1(),
        level2(),
    };

    public static Level level0()
    {
        Level level = new Level();
        level.setBallRadius(1.5);
        level.setBallPos(new Vector2(-10, -5));
        level.setHole(new Hole(8.0, level.getBallRadius() * 2 + 1.0));
        Obstacle rect1 = new Obstacle(new Rectangle(4.0, 1.0), 3.0, 10.0, false);
        Obstacle rect2 = new Obstacle(new Rectangle(3.0, 5.0), -2.0, 0.0, false);
        Obstacle circle1 = new Obstacle(new Circle(1.0), 5.0, 0.0, false);
        Obstacle circle2 = new Obstacle(new Circle(3.0), 7.0, -5.0, false);
        Obstacle capsule1 = new Obstacle(new Capsule(2.0, 4.0), -9.0, 8.0, true);
        Obstacle anchor = new Obstacle(new Polygon(new Vector2(0.0, 0.0)), capsule1.getX(), capsule1.getY() + 4.0, false);
        level.addObstacle(rect1);
        level.addObstacle(rect2);
        level.addObstacle(circle1);
        level.addObstacle(circle2);
        level.addObstacle(capsule1);
        level.addObstacle(anchor);
        RevoluteJoint<SimulationBody> pJoint = new RevoluteJoint<SimulationBody>(capsule1, anchor, new Vector2(anchor.getX(), anchor.getY()));
        //pJoint = pendulum joint. The capsule is simulated to be a physical pendulum.
        level.addJoint(pJoint);
        return level;
    }

    public static Level level1()
    {
        Level level = new Level();
        level.setBallPos(new Vector2(-15, 10));
        level.setBallRadius(1.0);
        level.setHole(new Hole(5.0, level.getBallRadius() * 2 + 0.5));
        
        return level;
    }

    public static Level level2()
    {
        Level level = new Level();
        level.setBallRadius(0.5);
        level.setBallPos(new Vector2(-3, -9));
        level.setHole(new Hole(8.0, level.getBallRadius() * 2 + 0.2));
        return level;
    }

    public static void main(String[] args) {
        for (Level level : LEVELS)
        {
            RoThro rothro = new RoThro(level);
            rothro.run();
        }
    }
}
