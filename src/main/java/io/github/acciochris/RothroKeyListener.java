package io.github.acciochris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RothroKeyListener implements KeyListener
{

    RothroInputManager im;
    public RothroKeyListener()
    {
        im = new RothroInputManager();
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        im.keyPressed(e.getKeyCode());
        
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        im.keyReleased(e.getKeyCode());
    }
    @Override
    /*
     * keyTyped is uneeded for this project
     */
    public void keyTyped(KeyEvent e){}

    public RothroInputManager getIm()
    {
        return im;
    }
}
