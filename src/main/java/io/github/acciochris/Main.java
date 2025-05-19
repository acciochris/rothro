package io.github.acciochris;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
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

        // Ball & Hole Settings for level0:
        level.setBallRadius(1.5);
        level.setBallPos(new Vector2(-10, -5));
        level.setHole(new Hole(8.0, level.getBallRadius() * 2 + 1.0));

        // Initialization of obstacle storage lists
        LinkedList<Obstacle> rectObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> circleObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> capsuleObs = new LinkedList<Obstacle>();
        // For this level, only capsuleObs will contain movable objects

        // Creation and addition of rectangle Obstacles
        Obstacle rect1 = new Obstacle(new Rectangle(4.0, 1.0), 3.0, 10.0, false);
        Obstacle rect2 = new Obstacle(new Rectangle(3.0, 5.0), -2.0, 0.0, false);
        rectObs.add(rect1);
        rectObs.add(rect2);

        // Creation and addition of circle Obstacles
        Obstacle circle1 = new Obstacle(new Circle(1.0), 5.0, 0.0, false);
        Obstacle circle2 = new Obstacle(new Circle(3.0), 7.0, -5.0, false);
        circleObs.add(circle1);
        circleObs.add(circle2);
    
        // Creation and addition of capsule Obstacles
        Obstacle capsule1 = new Obstacle(new Capsule(6.0, 1.5), -9.0, 8.0, new Color(0xBD93F9), true, "Revolute");
        capsuleObs.add(capsule1);

        // Creation of the joints list & addition of Obstacle lists
        ArrayList<Integer> joints = new ArrayList<Integer>();
        joints.add(2);
        level.setJoints(joints);
        level.addObstacle(rectObs);
        level.addObstacle(circleObs);
        level.addObstacle(capsuleObs);

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
