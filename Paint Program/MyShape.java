/* Name: Aaron Leung
 * Date: January 1, 2016
 * Subject: MyShape abstract class
 */

import java.awt.Color;
import java.awt.Graphics;

abstract class MyShape
{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;
    private boolean gradientStatus;
    private Color secondColor;
    private float strokeWidth;
    private boolean dashedStatus;
    private float strokeDashLength;
    
    //This method is the constructor the the shape. It takes the coordinates of the shape and its colors and sets its variables accordingly
    public MyShape(int x1, int y1, int x2, int y2, Color color, boolean gradientStatus, Color secondColor, float strokeWidth, boolean dashedStatus, float strokeDashLength)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.gradientStatus = gradientStatus;
        
        if (gradientStatus)
        {
            if(secondColor == null)
            {
                this.secondColor = Color.BLACK;
            }
            else
            {
            this.secondColor = secondColor;   
            }
        }
        else
        {
            this.secondColor = null;
        }
        
        setStrokeWidth(strokeWidth);
        this.dashedStatus = dashedStatus;
        
        if (dashedStatus)
        {
            setStrokeDashLength(strokeDashLength);
        }
        else
        {
            this.strokeDashLength = 1;
        }
    }
    
    //This method is the parameter-less constructor. It sets the coordinates to 0 and the color to BLACK as defaults if no parameters are given.
    public MyShape()
    {
        this(0,0,0,0,Color.BLACK, false, null, 1, false, 0);
    }
    
    //This method takes the x1 coordinate as a parameter. It then sets the x1 coordinate to the argument's value.
    protected void setX1(int x1)
    {
        this.x1 = x1;
    }
    
    //This method takes the y1 coordinate as a parameter. It then sets the y1 coordinate to the argument's value.
    protected void setY1(int y1)
    {
        this.y1 = y1;
    }
    
    //This method takes the x2 coordinate as a parameter. It then sets the x2 coordinate to the argument's value.
    protected void setX2(int x2)
    {
        this.x2 = x2;
    }
    
    //This method takes the y2 coordinate as a parameter. It then sets the y2 coordinate to the argument's value.
    protected void setY2(int y2)
    {
        this.y2 = y2;
    }
    
    //This method takes the color as a parameter. It then sets the color to the argument's value.
    protected void setColor(Color color)
    {
        this.color = color;
    }   
    
    //This method takes the gradient status as a parameter. It then sets the gradient status to the argument's value
    protected void setGradientStatus(boolean gradientStatus)
    {
        this.gradientStatus = gradientStatus;
    }
    
    //This method takes the second color as a parameter. It then sets the second color to the argument's value
    protected void setSecondColor(Color secondColor)
    {
        this.secondColor = secondColor;
    }
    
    //This method takes the stroke width as a parameter. It then sets the stroke width to the argument's value
    protected void setStrokeWidth(float strokeWidth)
    {
        if (strokeWidth == 0)
        {
            this.strokeWidth = 1;
        }
        else
        {
            this.strokeWidth = strokeWidth;
        }
    }
    
    //This method takes the dashed status as a parameter. It then sets the dashed status to the argument's value
    protected void setDashedStatus(boolean dashedStatus)
    {
        this.dashedStatus = dashedStatus;
    }
    
    //This method takes the gradient status as a parameter. It then sets the gradient status to the argument's value
    protected void setStrokeDashLength(float strokeDashLength)
    {

        if (strokeDashLength == 0)
        {
            this.strokeDashLength = 1;
        }
        else{
            this.strokeDashLength = strokeDashLength;
        }
    }
    
    
    //This method takes no parameters. It returns the value of the x1 coordinate.
    protected int getX1()
    {
        return x1;
    }
    
    //This method takes no parameters. It returns the value of the y1 coordinate.
    protected int getY1()
    {
        return y1;
    }
    
    //This method takes no parameters. It returns the value of the x2 coordinate.
    protected int getX2()
    {
        return x2;
    }
    
    //This method takes no parameters. It returns the value of the y2 coordinate.
    protected int getY2()
    {
        return y2;
    }
    
    //This method takes no parameters. It returns the color of the shape.
    protected Color getColor()
    {
        return color;
    }
    
    //This method takes no parameters. It returns the gradient status of the shape.
    protected boolean getGradientStatus()
    {
        return gradientStatus;
    }
    
    //This method takes no parameters. It returns the second color of the shape.
    protected Color getSecondColor()
    {
        return secondColor;
    }

    //This method takes no parameters. It returns the stroke width of the shape.
    protected float getStrokeWidth()
    {
        return strokeWidth;
    }
    
    //This method takes no parameters. It returns the stroke width of the shape.
    protected boolean getDashedStatus()
    {
        return dashedStatus;
    }
    
    //This method takes no parameters. It returns the stroke dash length of the shape.
    protected float getStrokeDashLength()
    {
        return strokeDashLength;
    }
    
    //This abstract method is intended for the subclasses to draw the shape.
    public abstract void draw (Graphics g);
}