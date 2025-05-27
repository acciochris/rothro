package io.github.acciochris;

import java.util.HashSet;

//Fuckin glorified list, I can't believe KeyListener won't allow me to just return KeyEvent values
public class RothroInputManager 
{
    private HashSet<Integer> keysPressed;
    public RothroInputManager()
    {
        keysPressed = new HashSet<Integer>();
    }

    public void keyPressed(int i)
    {
        keysPressed.add(i);
    }

    public void keyReleased(int i)
    {
        keysPressed.remove(i);
    }

    public HashSet<Integer> getKeysPressed()
    {
        return keysPressed;
    }
}
