package io.github.acciochris;

import java.util.HashSet;

/**
 * Key input manager for Rothro.
 * 
 * @author Justin Huang
 * @version May 30, 2025
 */
public class RothroInputManager 
{
    private HashSet<Integer> keysPressed;

    /**
     * Construct a new RothroInputManager with no keys depressed.
     */
    public RothroInputManager()
    {
        keysPressed = new HashSet<Integer>();
    }

    /**
     * Add new pressed key.
     * 
     * @param i keyCode of pressed key
     */
    public void keyPressed(int i)
    {
        keysPressed.add(i);
    }

    /**
     * Remove existing pressed key.
     * 
     * @param i keyCode of pressed key
     */
    public void keyReleased(int i)
    {
        keysPressed.remove(i);
    }

    /**
     * Getter for keysPressed
     * 
     * @return HashSet of keys that are currently depressed
     */
    public HashSet<Integer> getKeysPressed()
    {
        return keysPressed;
    }
}
