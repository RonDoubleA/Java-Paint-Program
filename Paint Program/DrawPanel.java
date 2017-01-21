/* Name: Aaron Leung
 * Date: January 14, 2016
 * Subject: DrawPanel Class
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import javax.swing.JLabel;

//Mouse handling classes
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Point;

import java.awt.image.BufferedImage;


public class DrawPanel extends JPanel
{
    //constants for shapes
    private final int LINE = 0;
    private final int RECTANGLE = 1;
    private final int OVAL = 2;
    private final int ROUNDRECTANGLE = 3;
    private final int FREEFORM = 4;
    
    //dynamic structures
    private DynamicQueue<MyShape> shapeObjects;
    private DynamicStack<MyShape> redo;
    
    
    //shape properties
    private int currentShapeType;
    private MyShape currentShapeObject;
    private Color currentFirstShapeColor;
    private boolean currentGradientStatus;
    private Color currentSecondShapeColor;
    private float currentStrokeWidth;
    private boolean currentDashedStatus;
    private float currentStrokeDashLength;
    private boolean currentShapeFilled;
    private JLabel statusLabel;
    
    //coordinates
    private int shapeX1;
    private int shapeY1;
    private int shapeX2;
    private int shapeY2;
    
    private int mouseKey;
    
    private DynamicQueue<Point> coords;
    
    //drawing or getting color
    private boolean draw;
    
    //if loaded image
    private BufferedImage bufferedImage;
    
    //This method is the initializer for the Draw Panel and takes a label as 
    public DrawPanel(JLabel label)
    {

        statusLabel = label;
        
        //drawing or getting color
        draw = true;
        
        shapeObjects = new DynamicQueue<MyShape>();
        redo = new DynamicStack<MyShape>();
        
        coords = new DynamicQueue<Point>();       
        
        currentShapeType = 0;
        currentFirstShapeColor = Color.BLACK;
        
        setBackground( Color.WHITE );
        
        //mouse handler
        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);       
    }
    
    //This method take the value for the shape type as a parameter. It then sets the current shape type to that value
    public void setCurrentShapeType(int shapeType)
    {
        currentShapeType = shapeType;
    }
    
    //This method take the value for the shape type as a parameter. It then sets the current shape type to that value
    public void setCurrentFirstShapeColor(Color color)
    {
        currentFirstShapeColor = color;
    }
    
    //This method take the value for the shape type as a parameter. It then sets the current shape type to that value
    public void setCurrentShapeFilled(boolean filled)
    {
        currentShapeFilled = filled;
    }
    
    //This method takes the status of gradient use as a parameter. It then sets the current shape type to that value
    public void setCurrentGradientStatus(boolean status)
    {
        currentGradientStatus = status;
    }
    
    //This method takes the second color as a parameter. It then sets the current second color to that value
    public void setCurrentSecondShapeColor(Color secondColor)
    {
        currentSecondShapeColor = secondColor;
    }
    
    //This method takes the stroke width as a parameter. It then sets the current stroke width to that value
    public void setCurrentStrokeWidth(float strokeWidth)
    {
        currentStrokeWidth = strokeWidth;
    }
    
    //This method takes the dashed status as a parameter. It then sets the current dashed status to that value
    public void setCurrentDashedStatus(boolean dashedStatus)
    {
        currentDashedStatus = dashedStatus;
    }
    
    //This method takes the stroke dash length as a parameter. It then sets the current stroke dash length to that value
    public void setCurrentStrokeDashLength(float strokeDashLength)
    {
        currentStrokeDashLength = strokeDashLength;
    }
    
    //This method clears all the shapes in the array list. It takes no parameters and returns nothing
    public void clearLastShape()
    {
        int size = shapeObjects.size();
        if (shapeObjects.size() > 0)
        {
            for (int i = 0; i < size; i++)
            {
                if (i != size - 1){
                    shapeObjects.enqueue(shapeObjects.dequeue());
                }
                else{
                    redo.push(shapeObjects.dequeue());                    
                }                
            }    
            repaint();     
        }
    }
    
    //This method takes no parameters and returns nothing. If there is an object to be remade, it will remake that shape
    public void redoLastShape()
    {
        if(redo.size() > 0){
            shapeObjects.enqueue(redo.pop());
            repaint();
        }
    }
    
    //This method takes no parameters and returns nothing. It clears all drawings on the screen and clears any redoable objects
    public void clearDrawing()
    {
        shapeObjects.makeEmpty();
        redo.makeEmpty();
        repaint();
        setBufferedImage(null);
    }
    
    //This method takes the boolean ad a varialble. It determines whether or not you can draw on the draw panel
    public void setDraw(boolean draw)
    {
        this.draw = draw;
    }
    
    
    //This method takes a buffered image as a parameter and sets it to that buffered image
    public void setBufferedImage(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
    }
    
    //This class handles all moust motion
    private class MouseHandler implements MouseListener, MouseMotionListener{
        
        //This method takes the mouse event as a parameter. When clicked, it sets the top left and bottom right corners for the shape to be drawn
        public void mousePressed(MouseEvent event){
            
            if (draw)
            {
                
                mouseKey = event.getButton();
                if (mouseKey == 1){
                    shapeX1 = event.getX();
                    shapeY1 = event.getY();
                    
                    int redoSize = redo.size();
                    for (int i = 0; i < redoSize; i++)
                    {
                        redo.pop();
                    }
                    
                    mouseDragged(event);
                }
            }
        }
        
        //This method takes the mouse event as a parameter. When the mouse is released, it finalizes the shape's bottom right corner and adds the shape to the arrayList
        public void mouseReleased(MouseEvent event) {
            
            if (draw)
            {
                mouseDragged(event);
                if (event.getButton() == 1){
                    
                    shapeObjects.enqueue(currentShapeObject);
                    currentShapeObject = null;
                    if (currentShapeType == FREEFORM)
                    {
                        coords = new DynamicQueue<Point>();
                    }
                }
            }
            
            
        }
        
        //This method takes the mouse event as a parameter. It updates the coordinates in the label with the mouse's coordinates
        public void mouseMoved(MouseEvent event) {
            
            statusLabel.setText(String.format("(%d, %d)", event.getX(), event.getY()));
        }
        
        //This method takes the mouse event as a parameter. When the mouse is dragged, it updates the bottom-right corner's coordinates and updates it so you can see the changes
        public void mouseDragged(MouseEvent event) {
            
            if (draw)
            {
                if (mouseKey == 1){
                    shapeX2 = event.getX();
                    shapeY2 = event.getY();
                }
                if (currentShapeType == LINE){
                    currentShapeObject = new MyLine(shapeX1, shapeY1, shapeX2, shapeY2, currentFirstShapeColor, currentGradientStatus, currentSecondShapeColor, currentStrokeWidth, currentDashedStatus, currentStrokeDashLength);
                }
                
                if (currentShapeType == RECTANGLE){
                    currentShapeObject = new MyRectangle(shapeX1, shapeY1, shapeX2, shapeY2, currentFirstShapeColor, currentGradientStatus, currentSecondShapeColor, currentStrokeWidth, currentDashedStatus, currentStrokeDashLength,currentShapeFilled);
                }
                
                if (currentShapeType == OVAL){
                    currentShapeObject = new MyOval(shapeX1, shapeY1, shapeX2, shapeY2, currentFirstShapeColor, currentGradientStatus, currentSecondShapeColor, currentStrokeWidth, currentDashedStatus, currentStrokeDashLength, currentShapeFilled);
                }
                
                if (currentShapeType == ROUNDRECTANGLE){
                    currentShapeObject = new MyRoundRectangle(shapeX1, shapeY1, shapeX2, shapeY2, currentFirstShapeColor, currentGradientStatus, currentSecondShapeColor, currentStrokeWidth, currentDashedStatus, currentStrokeDashLength, currentShapeFilled);
                }
                
                if (currentShapeType == FREEFORM)
                {
                    coords.enqueue(event.getPoint());
                    DynamicQueue<Point> temp = coords;
                    currentShapeObject = new MyFreeformLine(shapeX1, shapeY1, shapeX2, shapeY2, currentFirstShapeColor, currentGradientStatus, currentSecondShapeColor, currentStrokeWidth, currentDashedStatus, currentStrokeDashLength, temp);
                }
                repaint();
            }
            mouseMoved(event);
        }
        
        
        //This is an unused method from the MouseListener and MouseMotionListener class.
        public void mouseExited(MouseEvent event){
        }
        
        //This is an unused method from the MouseListener and MouseMotionListener class.
        public void mouseEntered(MouseEvent event){
        }
        
        //This is an unused method from the MouseListener and MouseMotionListener class.
        public void mouseClicked(MouseEvent event){
        }
        
    }
    
    
// this method takes the graphics as a parameter. It then draws the shapes on the graphics "canvas" before drawing it on the screen
    public void paintComponent( Graphics g )
    {
        
        super.paintComponent( g );
        
        if (bufferedImage != null)
        {
            g.drawImage(bufferedImage, 0, 0, null);
        }
        
        // draw the shapes
        for ( int counter = 0; counter < shapeObjects.size(); counter++ )
        {
            MyShape object = shapeObjects.dequeue();
            shapeObjects.enqueue(object);
            object.draw(g);
        }
        
        if (currentShapeObject != null)
        {
            currentShapeObject.draw(g);
        }
        
    } // end method paintComponent
} // end class DrawPanel