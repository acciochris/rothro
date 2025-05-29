package io.github.acciochris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Key listener for the RoThro game. Implements java.awt.event.KeyListener.
 * 
 * @author Justin Huang
 * @version May 30, 2025
 */
public class RothroKeyListener
    implements KeyListener
{
    private RothroInputManager im;

    /**
     * Construct a new RothroKeyListener.
     */
    public RothroKeyListener()
    {
        im = new RothroInputManager();
    }


    /**
     * Forward key presses to input manager.
     * 
     * @param e
     *            the key event
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        im.keyPressed(e.getKeyCode());

    }


    /**
     * Forward key releases to input manager.
     * 
     * @param e
     *            the key event
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        im.keyReleased(e.getKeyCode());
    }


    /**
     * keyTyped is unused for this project.
     * 
     * @param e
     *            unused
     */
    @Override
    public void keyTyped(KeyEvent e)
    {
    }


    /**
     * Getter for input manager
     * 
     * @return the input manager
     */
    public RothroInputManager getIm()
    {
        return im;
    }
}
