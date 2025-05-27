package io.github.acciochris;

/**
 * Represents the hole (goal) on the right wall.
 * 
 * @author Chris Liu
 * @author Anvesh Pattaje
 * @version May 30, 2025
 */
public class Hole 
{
    private double y;
    private double size;

    /**
     * Construct a new hole with y coordinate and diameter
     * 
     * @param y y coordinate of hole
     * @param size hole diameter
     */
    public Hole(double y, double size)
    {
        this.y = y;
        this.size = size;
    }

    /**
     * Getter for hole size.
     * 
     * @return hole diameter
     */
    public double getSize()
    {
        return size;
    }

    /**
     * Getter for hole y coordinate
     * 
     * @return y coordinate
     */
    public double getY()
    {
        return y;
    }
}
