/* Name: Aaron Leung
 * Date: November 30, 2015
 * Subject: DrawLine Class
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class MyLine extends MyShape
{
    //This constructor takes the coordinates and color as parameters and runs the MyShape constructor with those parameters
    public MyLine( int x1, int y1, int x2, int y2, Color color, boolean gradientStatus, Color secondColor, float strokeWidth, boolean dashed, float strokeDashLength)
    {
        super(x1, y1, x2, y2, color, gradientStatus, secondColor, strokeWidth, dashed, strokeDashLength);
    } // end MyLine constructor
    
    //This constructor runs the MyShape constructor without parameters
    public MyLine()
    {
        super();
    }
    
    // Actually draws the line
    public void draw( Graphics g )
    {
        Graphics2D g2d = (Graphics2D) g;
        
        //Gradients
        if (getGradientStatus())
        {
            g2d.setPaint(new GradientPaint(getX1(), getY1(), getColor(), getX2(), getY2(), getSecondColor()));
        }
        else{
            g2d.setColor( getColor());
        }
        
        //if dashed
        if (getDashedStatus())
        {
            float[] dashPattern = {getStrokeDashLength(), getStrokeDashLength()};
            g2d.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        }
        else{
            g2d.setStroke(new BasicStroke(getStrokeWidth()));
        }
        
        g2d.draw(new Line2D.Double(getX1(), getY1(), getX2(), getY2()));
    }
    // end method draw()
} // end class MyLine