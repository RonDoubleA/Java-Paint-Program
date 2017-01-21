/* Name: Aaron Leung
 * Date: January 1, 2016
 * Subject: MyBoundedShape class
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
    


abstract class MyBoundedShape extends MyShape
{
    private boolean filled;
    
    //This method is the constructor. It takes the coordinates of the shape, the color, and the filled status as parameters and initializes them
    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean gradient, Color secondColor, float strokeWidth, boolean dashed, float strokeDashLength, boolean filled)
    {
        super(x1, y1, x2, y2, color, gradient, secondColor, strokeWidth, dashed, strokeDashLength);
        this.filled = filled;
    }
    
    //This method is the constructor. It runs if no parameters are given and initializes the coordinates to 0, the color to black, and the filled status to false
    public MyBoundedShape()
    {
        super();
        this.filled = false;
    }
    
    //This method takes no parameters. It returns whether or not the shape is filled
    protected boolean getFilled()
    {
        return filled;
    }
    
    //This method takes a boolean parameter: whether or not the shape is filled. It then sets the shape's fill status to the parameter
    protected void setFilled(boolean filled)
    {
        this.filled = filled;
    }
    
    //This method takes no parameters. It calculates the location of the upper-left x coordinate and returns that value
    protected int getUpperLeftX()
    {
        if (getX1()<getX2())
        {
            return getX1();
        }
        else{
            return getX2();
        }
    }
    
    //This method takes no parameters. It calculates the location of the upper-left y coordinate and returns that value    
    protected int getUpperLeftY()
    {
        if (getY1()<getY2())
        {
            return getY1();
        }
        else{
            return getY2();
        }
    }
    
    //This method takes no parameters. It calculates the width of the shape and returns that value
    protected int getWidth()
    {

        return Math.abs(getX2()-getX1());
    }
    
    //This method takes no parameters. It calculates the height of the shape and returns that value
    protected int getHeight()
    {
        return Math.abs(getY2()-getY1());
    }
    //This abstract method is intended for the subclasses to draw the shape.    
    public abstract void draw(Graphics g);
}