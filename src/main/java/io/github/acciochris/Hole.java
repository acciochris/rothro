package io.github.acciochris;

public class Hole 
{
    private double y;
    private double size;

    public Hole(double y, double size)
    {
        this.y = y;
        this.size = size;
    }

    public double getSize()
    {
        return size;
    }

    public double getY()
    {
        return y;
    }
}
