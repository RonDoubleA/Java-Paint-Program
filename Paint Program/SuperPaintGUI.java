/* Name: Aaron Leung
 * Date: April 14, 2016
 * Subject: Super Paint GUI Test File
 */



import javax.swing.JFrame;

public class SuperPaintGUI
{
    //This method is the mainline code
    public static void main(String args[])
    {
        DrawFrame drawFrame = new DrawFrame();
        drawFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        drawFrame.setSize(1000, 500); // set frame size
        drawFrame.setVisible( true ); // display frame
    } // end main
} // end class ComboBoxTest