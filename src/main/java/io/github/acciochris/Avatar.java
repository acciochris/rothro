package io.github.acciochris;

import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;
import java.awt.event.KeyEvent;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Vector2;
import io.github.acciochris.framework.SimulationBody;

public class Avatar extends SimulationBody
{
    private ArrayList<ArrayList<Integer>> controls;
    private double r;

    public Avatar()
    {
        super(new Color(0xF8F8F2));
        r = 1;
        addFixture(new Circle(r));
        setMass(MassType.NORMAL);

        controls = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= 5; i++)
        {
            controls.add(new ArrayList<Integer>());
        }
        controls.get(0).add(KeyEvent.VK_LEFT);
        controls.get(1).add(KeyEvent.VK_RIGHT);
        controls.get(2).add(KeyEvent.VK_UP);
        controls.get(3).add(KeyEvent.VK_DOWN);
        controls.get(4).add(KeyEvent.VK_SPACE);
        this.setMassType(MassType.NORMAL);
    }

        /**
     * Calling this function results in a logic chain that allows the user to change what certain keys do
     * @param option - what to do to the subject control
     * @param subjectControl - the control in question to change
     * @param replacementControl - the control to replace it with
     */
    public void setControls( char option, int subjectControl, int replacementControl)
    {
        if (option == 'd')
        {
            for (int a: controls.get(subjectControl))
            {
                controls.get(subjectControl).remove(a);
            }
        }
        if (option == 'a')
        {
            controls.get(subjectControl).add(replacementControl);
        }
    }

    public void controls(HashSet<Integer> keysPressed)
    {
        for (int i: keysPressed)
        {
            /*
             * Controls Movement
             */
            if (controls.get(0).contains(i))
            {
                this.applyImpulse(new Vector2(-0.6, 0));
            }
            if (controls.get(1).contains(i))
            {
                this.applyImpulse(new Vector2(0.6, 0));
            }
            if (controls.get(2).contains(i))
            {
                this.applyImpulse(new Vector2(0, 0.6));
            }
            if (controls.get(3).contains(i))
            {
                this.applyImpulse(new Vector2(0, -0.6));
            }
        }
    }
}
