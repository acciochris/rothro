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
        level.setBallPos(new Vector2(-12, -3));
        level.setHole(new Hole(8.0, level.getBallRadius() * 2 + 1.0));

        // Initialization of obstacle storage lists
        LinkedList<Obstacle> statyRectObs = new LinkedList<Obstacle>(); // staty = stationary
        LinkedList<Obstacle> statyCircleObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> capsuleObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> rectObs = new LinkedList<Obstacle>();

        // Creation and addition of rectangle Obstacles
        Obstacle rect1 = new Obstacle(new Rectangle(4.0, 1.0), 5.0, 0.0, false);
        Obstacle rect2 = new Obstacle(new Rectangle(3.5, 4.0), -2.0, 7.0, new Color(90, 40, 180), true, "Prismatic", "FIXANG");
        statyRectObs.add(rect1);
        rectObs.add(rect2);

        // Creation and addition of circle Obstacles
        Obstacle circle1 = new Obstacle(new Circle(2.75), 10.0, 6.0, false);
        Obstacle circle2 = new Obstacle(new Circle(3), 9.0, -6.0, false);
        statyCircleObs.add(circle1);
        statyCircleObs.add(circle2);
    
        // Creation and addition of capsule Obstacles
        Obstacle capsule1 = new Obstacle(new Capsule(6.0, 1.5), -9.0, 8.0, new Color(0xBD93F9), true, "Revolute", "NORM");
        Obstacle capsule2 = new Obstacle(new Capsule(6.0, 1.5), -9.0, -8.0, new Color(30, 140, 90), true, "Revolute", "NORM");
        capsuleObs.add(capsule1);
        capsuleObs.add(capsule2);

        // Creation of the joints list & addition of Obstacle lists
        ArrayList<Integer> joints = new ArrayList<Integer>();
        joints.add(2);
        joints.add(3);
        level.setJoints(joints);
        level.addObstacle(statyRectObs);
        level.addObstacle(statyCircleObs);
        level.addObstacle(rectObs);
        level.addObstacle(capsuleObs);

        return level;
    }

    public static Level level1()
    {
        Level level = new Level();
        level.setBallPos(new Vector2(-15, 10));
        level.setBallRadius(1.0);
        level.setHole(new Hole(5.0, level.getBallRadius() * 2 + 0.5));

        // Initialization of obstacle storage lists
        LinkedList<Obstacle> statyRectObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> statyCircleObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> capsuleObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> rectObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> triObs = new LinkedList<Obstacle>();

        // Creation and addition of rectangle Obstacles
        Obstacle rect1 = new Obstacle(new Rectangle(2, 7.5), -9.0, -3.0, new Color(90, 80, 170), false, "", "");
        Obstacle rect2 = new Obstacle(new Rectangle(7.5, 2.5), -4.25, -8.0, new Color(90, 80, 170), false, "", "");
        statyRectObs.add(rect1);
        statyRectObs.add(rect2);
        // statyRectObs.add(rect2);
        // statyRectObs.add(rect2);
        // statyRectObs.add(rect2);

        // Creation and addition of circle Obstacles
        Obstacle circle1 = new Obstacle(new Circle(2.25), -1.0, 8.0, new Color(190, 180, 0), false, "", "");
        statyCircleObs.add(circle1);
    
        // Creation and addition of capsule Obstacles

        // Creation and addition of triangle Obstacles
        Obstacle tri1 = new Obstacle(new Triangle(new Vector2(3.0, -3.0), new Vector2(0.0, 3.0), new Vector2(-3.0, -3.0)), 10.0, -4.0, new Color(60, 230, 190), true, "Revolute", "NORM");
        Obstacle tri2 = new Obstacle(new Triangle(new Vector2(3.0, -3.0), new Vector2(0.0, 3.0), new Vector2(-3.0, -3.0)), 8.0, 5.0, new Color(60, 230, 190), true, "Revolute", "NORM");
        triObs.add(tri1);
        triObs.add(tri2);

        // Creation and addition of Polygon Obstacles

        // Creation of the joints list & addition of Obstacle lists
        ArrayList<Integer> joints = new ArrayList<Integer>();
        joints.add(4);
        level.setJoints(joints);
        level.addObstacle(statyRectObs);
        level.addObstacle(statyCircleObs);
        level.addObstacle(rectObs);
        level.addObstacle(capsuleObs);
        level.addObstacle(triObs);

        return level;
    }

    public static Level level2()
    {
        Level level = new Level();
        level.setBallRadius(0.5);
        level.setBallPos(new Vector2(-3, -9));
        level.setHole(new Hole(8.0, level.getBallRadius() * 2 + 0.1));

        // Initialization of obstacle storage lists
        LinkedList<Obstacle> statyRectObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> statyCircleObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> capsuleObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> rectObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> triObs = new LinkedList<Obstacle>();
        LinkedList<Obstacle> polygonObs = new LinkedList<Obstacle>();

        // Creation and addition of rectangle Obstacles

        // Creation and addition of circle Obstacles
    
        // Creation and addition of capsule Obstacles

        // Creation and addition of Polygon Obstacles

        // Creation and addition of Triangle Obstacles

        // Creation of the joints list & addition of Obstacle lists
        ArrayList<Integer> joints = new ArrayList<Integer>();
        level.setJoints(joints);
        level.addObstacle(statyRectObs);
        level.addObstacle(statyCircleObs);
        level.addObstacle(rectObs);
        level.addObstacle(capsuleObs);
        level.addObstacle(triObs);
        level.addObstacle(polygonObs);

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
