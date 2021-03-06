/* Name: Aaron Leung
 * Date: May 29, 2016
 * Subject: Freeform Line Drawing
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Point;

public class MyFreeformLine extends MyShape
{
    
    private DynamicQueue<Point> coords;
    
    @SuppressWarnings("unchecked")
    //This constructor takes the coordinates and color as parameters and runs the MyShape constructor with those parameters
        public MyFreeformLine( int x1, int y1, int x2, int y2, Color color, boolean gradientStatus, Color secondColor, float strokeWidth, boolean dashed, float strokeDashLength, DynamicQueue<Point> coords)
    {
        super(x1, y1, x2, y2, color, gradientStatus, secondColor, strokeWidth, dashed, strokeDashLength);
        
        
        this.coords = coords;
    } // end MyFreeformLine constructor
    
    //This constructor runs the MyShape constructor without parameters
    public MyFreeformLine()
    {
        super();
        this.coords = null;
    }
    
    // Actually draws the line
    public void draw( Graphics g )
    {
        Graphics2D g2d = (Graphics2D) g;
        
        int pixelCount = coords.size();
        
        boolean drawIt = true;
        
        g2d.setColor(getColor());
        
        //color components for gradient
        int r1 = 0;
        int g1 = 0;
        int b1 = 0;
        int r2 = 0;
        int g2 = 0;
        int b2 = 0;
        int dr = 0;
        int dg = 0;
        int db = 0;
        
        //manually doing the gradient
        if (getGradientStatus())
        {
            r1 = getColor().getRed();
            g1 = getColor().getGreen();
            b1 = getColor().getBlue();
            
            r2 = getSecondColor().getRed();
            g2 = getSecondColor().getGreen();
            b2 = getSecondColor().getBlue();
            
            dr = r2 - r1;
            dg = g2-g1;
            db = b2 - b1;
        }
        
        Point point1;
        Point point2 = null;
        for (int i = 0; i < pixelCount - 1; i++)
        {
            if (i == 0)
            {
                point1 = coords.dequeue();
                
                coords.enqueue(point1);
            }
            
            else
            {
                point1 = point2;
            }
            
            point2 = coords.dequeue();
            coords.enqueue(point2);
            
            //manually doing gradient
            if (getGradientStatus())
            {
                g2d.setColor(new Color((int)(r1 + (dr * (i * 1.0 / pixelCount))), (int)(g1 + (dg * (i * 1.0 / pixelCount))), (int)((b1 + (db * (i * 1.0 / pixelCount))))));
            }
            
            //dashed lines
            if (getDashedStatus())
            {
                float[] dashPattern = {getStrokeDashLength(), getStrokeDashLength()};
                g2d.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
            }
            else{
                g2d.setStroke(new BasicStroke(getStrokeWidth()));
            }
            
            g2d.draw(new Line2D.Double(point1.x, point1.y, point2.x, point2.y)); 
        }
    }
    
// end method draw()
}
// end class MyFreeformLine