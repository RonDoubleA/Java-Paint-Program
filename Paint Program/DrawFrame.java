/* Name: Aaron Leung
 * Date: April 14, 2016
 * Subject: DrawFrame class
 */


import java.awt.Color;

//action listeners 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

//layouts
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;

//jpanel components
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar; 
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JColorChooser;

//color chooser
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;


//File stuff
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import javax.imageio.ImageIO;


public class DrawFrame extends JFrame{
    
    private DrawPanel drawPanel;
    
    
    //Panel with all buttons
    private JPanel topBar;
    
    //buttons
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    
    //Color Buttons
    
    private Color firstColor;
    private Color secondColor;  
    private Color tempColor;
    private JButton firstColorButton;
    private JButton secondColorButton;
    private JButton swapColorButton;
    private JButton colorPicker;
    private ImageIcon colorPickerIcon;
    
    
    //Shape ComboBox
    private JComboBox shapeComboBox;
    private String shapes[] = {"Line", "Rectangle", "Oval", "Round Rectangle", "Freeform"};
    
    //Filled CheckBox
    private JCheckBox filled;
    
    //Gradient Status Checkbox
    private JCheckBox gradient;
    
    //Dashed Status Checkbox
    private JCheckBox dashed;
    
    //Textboxes
    private JLabel strokeWidthLabel;
    private JTextField strokeWidth;
    private JLabel strokeDashLengthLabel;
    private JTextField strokeDashLength;
    
    private JScrollPane scrollPane;
    private BorderLayout layout;
    
    //Label for Mouse Coordinates
    private JLabel statusBar;
    
    //Menu Items
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem about;
    private JMenuItem loadImage;
    private JMenuItem saveImage;
    private JMenuItem saveAs;
    private JMenuItem preferences;
    private JMenuItem exit;
    private JMenu editMenu;
    private JMenuItem undo;
    private JMenuItem redo;
    
    //Preferences Stuff
    private JButton prefColorButton;
    private Color prefColor;
    private JCheckBox prefGradientCheckBox;
    private JButton prefSecondColorButton;
    private Color prefSecondColor;
    private JComboBox prefShapeComboBox;
    private JCheckBox prefFilledCheckBox;
    private JLabel prefStrokeWidthLabel;
    private JTextField prefStrokeWidthTextField;
    private JCheckBox prefDashedLinesCheckBox;
    private JLabel prefStrokeDashLengthLabel;
    private JTextField prefStrokeDashLengthTextField;
    
    //Preference JButtons
    private JButton upload;
    private JButton load;
    private JButton save;
    private JButton use;
    
    
    //drawing vs getting color
    private boolean draw;
    private BufferedImage bi;
    
    private String loadedFileName;
    
    
    public DrawFrame()
    {
        super("SuperPaintGUI");
        layout = new BorderLayout(5, 5);
        
        setLayout(layout);
        
        //initialize the top bar
        
        //menu bar items
        
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        //file
        fileMenu = new JMenu("File");
        
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        about = new JMenuItem("About");
        loadImage = new JMenuItem("Load Image");
        
        saveImage = new JMenuItem("Save Image");
        KeyStroke ctrlSKey = KeyStroke.getKeyStroke("control S");
        saveImage.setAccelerator(ctrlSKey);
        
        
        saveAs = new JMenuItem("Save Image As");
        preferences = new JMenuItem("Prefs");
        exit = new JMenuItem("Exit");
        
        //edit
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        
        undo = new JMenuItem("Undo");
        KeyStroke ctrlZKey = KeyStroke.getKeyStroke("control Z");
        undo.setAccelerator(ctrlZKey);
        editMenu.add(undo);
        
        redo = new JMenuItem("Redo");
        KeyStroke ctrlShiftZKey = KeyStroke.getKeyStroke("control shift Z");
        redo.setAccelerator(ctrlShiftZKey);
        editMenu.add(redo);
        
        //Menu Handlers
        MenuHandler menuHandler = new MenuHandler();
        about.addActionListener(menuHandler);
        preferences.addActionListener(menuHandler);
        loadImage.addActionListener(menuHandler);
        saveImage.addActionListener(menuHandler);
        saveAs.addActionListener(menuHandler);
        exit.addActionListener(menuHandler);
        
        undo.addActionListener(menuHandler);
        redo.addActionListener(menuHandler);
        
        fileMenu.add(about);
        fileMenu.add(loadImage);
        fileMenu.add(saveImage);
        fileMenu.add(saveAs);
        fileMenu.add(preferences);
        fileMenu.add(exit);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        //top bar options
        topBar = new JPanel();
        topBar.setLayout(new GridLayout(2, 8, 2, 2));
        
        //buttons
        undoButton = new JButton("Undo");
        redoButton = new JButton("Redo");
        clearButton = new JButton("Clear");
        
        topBar.add(undoButton);
        topBar.add(redoButton);
        topBar.add(clearButton);
        
        //button handling
        ButtonHandler buttonHandler = new ButtonHandler();
        undoButton.addActionListener(buttonHandler);
        redoButton.addActionListener(buttonHandler);
        clearButton.addActionListener(buttonHandler);
        
        //First Color Button
        firstColorButton = new JButton("First Color");
        firstColor = Color.BLACK;
        firstColorButton.setBackground(Color.BLACK);
        firstColorButton.setForeground(Color.WHITE);
        
        
        //gradient status checkbox
        
        gradient = new JCheckBox("Use Gradient");
        
        
        //Second Color Button
        
        secondColorButton = new JButton("Second Color");
        secondColor = Color.BLACK;
        secondColorButton.setBackground(Color.BLACK);
        secondColorButton.setForeground(Color.WHITE);
        
        secondColorButton.setEnabled(false);
        
        //swaps colors
        swapColorButton = new JButton("Swap Colors");
        
        //need this for color choosing
        colorPickerIcon = new ImageIcon("Color Picker.png");
        colorPicker = new JButton(colorPickerIcon);
        draw = true;
        
        firstColorButton.addActionListener(buttonHandler);
        secondColorButton.addActionListener(buttonHandler);
        swapColorButton.addActionListener(buttonHandler);
        colorPicker.addActionListener(buttonHandler);
        
        //Shape Combo Box
        shapeComboBox = new JComboBox(shapes);
        shapeComboBox.setMaximumRowCount(5);
        
        //topBar.add(firstColorComboBox);
        topBar.add(firstColorButton);
        topBar.add(gradient);
        topBar.add(secondColorButton);
        topBar.add(swapColorButton);
        topBar.add(colorPicker);
        
        topBar.add(shapeComboBox);
        
        //ComboBox Handling
        ComboBoxHandler comboBoxHandler = new ComboBoxHandler();
        shapeComboBox.addItemListener(comboBoxHandler);
        
        //filled checkbox
        filled = new JCheckBox("Filled");
        topBar.add(filled);
        
        
        //stroke width text field
        strokeWidthLabel = new JLabel("Stroke Width:", SwingConstants.RIGHT);
        strokeWidth = new JTextField(5);
        strokeWidth.setText("1");
        topBar.add(strokeWidthLabel);
        topBar.add(strokeWidth);
        
        //dashed status checkbox
        
        dashed = new JCheckBox("Dashed Lines");
        topBar.add(dashed);
        
        //stroke dash length text field
        strokeDashLengthLabel = new JLabel("Stroke Dash Length:", SwingConstants.RIGHT);
        strokeDashLength = new JTextField(5);
        strokeDashLength.setText("1");
        
        strokeDashLength.setEnabled(false);
        
        topBar.add(strokeDashLengthLabel);
        topBar.add(strokeDashLength);
        
        //checkbox handler
        CheckBoxHandler checkBoxHandler = new CheckBoxHandler();
        filled.addItemListener(checkBoxHandler);
        gradient.addItemListener(checkBoxHandler);
        dashed.addItemListener(checkBoxHandler);
        
        //textfield handler
        TextFieldHandler textFieldHandler = new TextFieldHandler();
        strokeWidth.addActionListener(textFieldHandler);
        strokeDashLength.addActionListener(textFieldHandler);
        
        add(topBar, BorderLayout.NORTH);
        
        
        
        //status bar
        statusBar = new JLabel("");
        
        drawPanel = new DrawPanel(statusBar);
        
        
        drawPanel.addMouseListener(new MouseClickHandler());
        add(drawPanel, BorderLayout.CENTER);
        
        add(statusBar, BorderLayout.SOUTH);
        
    }
    
    //set up buffered image to get color
    private BufferedImage createImage(JPanel panel)
    {
        
        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        panel.paint(bi.getGraphics());
        return bi;
    }
    
    //This class handles all actions for the buttons
    private class ButtonHandler implements ActionListener
    {
        //This method takes the event as a parameter. It then runs methods from drawPanel based on what button was pressed and does not return anything
        public void actionPerformed(ActionEvent event)
        {
            //undo button
            if (event.getSource() == undoButton)
            {
                drawPanel.clearLastShape();
            }
            
            //redo button
            if (event.getSource() == redoButton)
            {
                drawPanel.redoLastShape();
            }
            
            //clear button
            if (event.getSource() == clearButton)
                
            {
                drawPanel.clearDrawing();
            }
            
            //color chooser
            if (event.getSource() == firstColorButton || event.getSource() == secondColorButton || event.getSource() == prefColorButton || event.getSource() == prefSecondColorButton)
            {
                Object[] data;
                
                
                JFrame colorChooserFrame = new JFrame("Color Chooser Screen");
                colorChooserFrame.setLayout(new BorderLayout(5, 5));
                colorChooserFrame.setSize(700, 500);
                JColorChooser colorChooser = new JColorChooser(Color.BLACK);
                
                colorChooser.getSelectionModel().addChangeListener(
                                                                   new ChangeListener()
                                                                       {
                    public void stateChanged(ChangeEvent e){
                        tempColor = colorChooser.getColor(); 
                    }
                }
                );
                JButton ok = new JButton("Okay");
                JButton cancel = new JButton("Cancel");
                //event handling for ok button
                ok.addActionListener(new ActionListener()
                                         {
                    public void actionPerformed(ActionEvent e)
                    {
                        
                        if (event.getSource() == firstColorButton)
                        {                            
                            firstColor = tempColor;
                            setButtonColors(firstColor, firstColorButton);
                        }
                        else if (event.getSource() == secondColorButton){
                            secondColor = tempColor;
                            
                            
                            setButtonColors(secondColor, secondColorButton);
                            
                        }
                        else if (event.getSource() == prefColorButton){
                            prefColor = tempColor;
                            setButtonColors(prefColor, prefColorButton);
                        }
                        else
                        {
                            prefSecondColor = tempColor;
                            setButtonColors(prefSecondColor, prefSecondColorButton);
                        }
                        colorChooserFrame.dispose();
                        
                    }
                }
                );
                
                //event handling for cancel button
                cancel.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent event)
                    {
                        colorChooserFrame.dispose();
                    }
                }
                );
                
                colorChooserFrame.add(colorChooser, BorderLayout.CENTER);
                colorChooserFrame.add(ok, BorderLayout.WEST);
                colorChooserFrame.add(cancel, BorderLayout.EAST);
                
                colorChooserFrame.setDefaultCloseOperation(colorChooserFrame.DISPOSE_ON_CLOSE);
                colorChooserFrame.setVisible(true);
                
            }
            
            //swap colors
            if (event.getSource() == swapColorButton)
            {
                Color temp = firstColor;
                firstColor = secondColor;
                secondColor = temp;
                
                setButtonColors(firstColor, firstColorButton);
                setButtonColors(secondColor, secondColorButton);
            }     
            
            //use settings in paint as preferences
            if (event.getSource() == upload)
            {
                prefColor = firstColor;
                setButtonColors(prefColor, prefColorButton); 
                
                prefSecondColor = secondColor;
                setButtonColors(prefSecondColor, prefSecondColorButton);
                
                prefFilledCheckBox.setSelected(filled.isSelected());
                prefGradientCheckBox.setSelected(gradient.isSelected());
                prefDashedLinesCheckBox.setSelected(dashed.isSelected());
                
                prefShapeComboBox.setSelectedIndex(shapeComboBox.getSelectedIndex());
                
                prefStrokeWidthTextField.setText(strokeWidth.getText());
                prefStrokeDashLengthTextField.setText(strokeDashLength.getText());                  
            }
            
            //use settings in preferences in paint
            if (event.getSource() == use)
            {
                firstColor = prefColor;
                setButtonColors(firstColor, firstColorButton);
                drawPanel.setCurrentFirstShapeColor(firstColor);
                
                secondColor = prefSecondColor;
                setButtonColors(secondColor, secondColorButton);
                drawPanel.setCurrentSecondShapeColor(secondColor);
                
                filled.setSelected(prefFilledCheckBox.isSelected());
                drawPanel.setCurrentShapeFilled(filled.isSelected());
                
                gradient.setSelected(prefGradientCheckBox.isSelected());
                drawPanel.setCurrentGradientStatus(gradient.isSelected());
                if (gradient.isSelected() == false)
                {
                    secondColorButton.setEnabled(false);
                }
                else{
                    secondColorButton.setEnabled(true);
                }
                
                dashed.setSelected(prefDashedLinesCheckBox.isSelected());
                drawPanel.setCurrentDashedStatus(dashed.isSelected());
                if (dashed.isSelected() == false)
                {
                    strokeDashLength.setEnabled(false);
                }
                else{
                    strokeDashLength.setEnabled(true);
                }
                
                shapeComboBox.setSelectedIndex(prefShapeComboBox.getSelectedIndex());
                drawPanel.setCurrentShapeType(shapeComboBox.getSelectedIndex());
                
                strokeWidth.setText(prefStrokeWidthTextField.getText());
                if (strokeWidth.getText().matches("\\d*[.]?\\d*") && strokeWidth.getText() != null)
                {
                    try
                    {
                        drawPanel.setCurrentStrokeWidth(Float.parseFloat(strokeWidth.getText()));  
                    }
                    catch (NumberFormatException numberFormatException)
                    {
                        drawPanel.setCurrentStrokeWidth(0);
                    }
                }
                
                strokeDashLength.setText(prefStrokeDashLengthTextField.getText());
                if (strokeDashLength.getText().matches("\\d*[.]?\\d*") && strokeDashLength.getText() != null)
                {
                    try{
                        drawPanel.setCurrentStrokeDashLength(Float.parseFloat(strokeDashLength.getText()));       
                    }
                    catch(NumberFormatException numberFormatException)
                    {
                        drawPanel.setCurrentStrokeDashLength(0);
                    }
                }
            }
            
            //load preferences from file
            if (event.getSource() == load)
            {
                String data;
                
                try{
                    Scanner fileInput = new Scanner(new File("preferences.txt"));
                    
                    prefColor = new Color(Integer.parseInt(fileInput.next().trim()));
                    setButtonColors(prefColor, prefColorButton); 
                    
                    prefSecondColor = new Color(Integer.parseInt(fileInput.next().trim()));
                    setButtonColors(prefSecondColor, prefSecondColorButton);
                    
                    prefShapeComboBox.setSelectedIndex(Integer.parseInt(fileInput.next().trim()));
                    
                    prefFilledCheckBox.setSelected(Boolean.parseBoolean(fileInput.next().trim()));
                    prefGradientCheckBox.setSelected(Boolean.parseBoolean(fileInput.next().trim()));
                    prefDashedLinesCheckBox.setSelected(Boolean.parseBoolean(fileInput.next().trim()));
                    
                    
                    
                    prefStrokeWidthTextField.setText(fileInput.next().trim());
                    prefStrokeDashLengthTextField.setText(fileInput.next().trim());  
                    
                    fileInput.close();
                    
                }
                catch (IOException ioException){
                    JOptionPane.showMessageDialog(null, "Error: File (probably) does not exist");
                }
                
            }
            
            //save preferences to file
            if (event.getSource() == save)
            {
                if (prefStrokeWidthTextField.getText().matches("\\d*[.]?\\d*") == false || prefStrokeDashLengthTextField.getText().matches("\\d*[.]?\\d*") == false)
                {
                    JOptionPane.showMessageDialog(null, "INVALID STROKE WIDTH OR DASH LENGTH VALUES");
                }
                else{
                    try{
                        PrintWriter fileOutput = new PrintWriter("preferences.txt");
                        fileOutput.println(prefColor.getRGB());
                        fileOutput.println(prefSecondColor.getRGB());
                        fileOutput.println(prefShapeComboBox.getSelectedIndex());
                        fileOutput.println(prefFilledCheckBox.isSelected());
                        fileOutput.println(prefGradientCheckBox.isSelected());
                        fileOutput.println(prefDashedLinesCheckBox.isSelected());
                        
                        
                        try
                        {
                            fileOutput.println(Float.parseFloat(prefStrokeWidthTextField.getText()));  
                        }
                        catch (NumberFormatException numberFormatException)
                        {
                            fileOutput.println(1);
                        }
                        
                        try
                        {
                            fileOutput.println(Float.parseFloat(prefStrokeDashLengthTextField.getText()));
                        }
                        catch (NumberFormatException numberFormatException)
                        {
                            fileOutput.println(1);
                        }
                        
                        
                        fileOutput.close();
                    }
                    catch (IOException ioException)
                    {
                        JOptionPane.showMessageDialog(null, "Error, could not save for some reason");
                    }
                }
                
            }   
            //color picker
            if (event.getSource() == colorPicker)
            {
                draw = !draw;
                drawPanel.setDraw(draw);
                if (draw)
                {
                    colorPicker.setBackground(Color.WHITE);
                }
                else{
                    colorPicker.setBackground(Color.YELLOW);
                }
            }
        }
    }
    //This method takes 2 parameters
    private void setButtonColors(Color colors, JButton button)
    {
        Color color;
        
        if (colors == null)
        {
            color = Color.WHITE;
        }
        else
        {
            color = colors;
        }
        
        button.setBackground(color);
        
        button.setForeground(new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue()));
        if (button == firstColorButton)
        {                
            drawPanel.setCurrentFirstShapeColor(color);
        }
        else if (button == secondColorButton)
        {
            drawPanel.setCurrentSecondShapeColor(color);
        }
        
    }
    
    //This class handles the actions of the combo boxes
    private class ComboBoxHandler implements ItemListener
    {
        //This method takes the event as a parameter. It then adjusts the appropriate setting based on the purpose of the combobox
        
        public void itemStateChanged(ItemEvent event)
        {
            //shapes
            if (event.getSource() == shapeComboBox)
            {
                if (event.getStateChange() == ItemEvent.SELECTED)
                {
                    drawPanel.setCurrentShapeType(shapeComboBox.getSelectedIndex());
                }
            }
        }
    }
    
    //This class handles the actions of the check boxes
    private class CheckBoxHandler implements ItemListener
    {
        //This method takes the event as a parameter. It then alters the setting for whether or not hte shape is filled based on the checkbox
        public void itemStateChanged(ItemEvent event)
        {
            //filled
            if (event.getSource() == filled)
            {
                drawPanel.setCurrentShapeFilled(filled.isSelected());
            }
            //gradient use
            if (event.getSource() == gradient)
            {
                drawPanel.setCurrentGradientStatus(gradient.isSelected());
                secondColorButton.setEnabled(gradient.isSelected());
                
            }
            //dash use
            if(event.getSource() == dashed)
            {
                drawPanel.setCurrentDashedStatus(dashed.isSelected());
                strokeDashLength.setEnabled(dashed.isSelected());
            }
            
            if (event.getSource() == prefGradientCheckBox)
            {
                prefSecondColorButton.setEnabled(prefGradientCheckBox.isSelected());
            }
            
            if (event.getSource() == prefDashedLinesCheckBox)
            {
                prefStrokeDashLengthTextField.setEnabled(prefDashedLinesCheckBox.isSelected());
            }
        }
    }
    //This class handles the actions of the text fields
    private class TextFieldHandler implements ActionListener
    {
        //This method takes the event as a parameter. It then sets the appropriate values to the parameters
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == strokeWidth)
            {
                if (event.getActionCommand().matches("\\d*[.]?\\d*"))
                {
                    drawPanel.setCurrentStrokeWidth(Float.parseFloat(event.getActionCommand()));                               
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "ERROR: Invalid Value. Please enter a float.");
                }
            }
            if (event.getSource() == strokeDashLength)
            {
                if (event.getActionCommand().matches("\\d*[.]?\\d*"))
                {
                    drawPanel.setCurrentStrokeDashLength(Float.parseFloat(event.getActionCommand()));                               
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "ERROR: Invalid Value. Please enter a float.");
                }
            }
        }
    }
    
    //This class handles the actions of the menu buttons
    private class MenuHandler implements ActionListener
    {
        //This method handles the actions of the text fields
        public void actionPerformed(ActionEvent event)
        {
            //about
            if (event.getSource() == about)
            {
                
                JOptionPane.showMessageDialog(null, "Java SuperPaint Assignment v1.0, by Aaron Leung.\nCompleted June 2016");
            }
            
            //preferences
            if (event.getSource() == preferences)
            {
                
                JFrame prefs = new JFrame("Set your preferences");
                prefs.setLayout(new BorderLayout(5, 5));
                prefs.setSize(1000, 200);
                
                JPanel prefComponents = new JPanel();
                prefComponents.setLayout(new GridLayout(2, 5, 20, 20));
                
                prefColor = Color.BLACK;
                prefSecondColor=Color.BLACK;
                
                //all jcomponents
                prefColorButton = new JButton("Main Color");
                prefColorButton.setBackground(Color.BLACK);
                prefColorButton.setForeground(Color.WHITE);
                
                prefGradientCheckBox = new JCheckBox("Use Gradient");
                
                prefSecondColorButton = new JButton("Second Color");
                prefSecondColorButton.setBackground(Color.BLACK);
                prefSecondColorButton.setForeground(Color.WHITE);
                prefSecondColorButton.setEnabled(false);
                
                prefShapeComboBox = new JComboBox(shapes);
                
                prefFilledCheckBox = new JCheckBox("Filled");
                
                prefStrokeWidthLabel = new JLabel("Stroke Width:");
                prefStrokeWidthTextField = new JTextField(5);
                prefStrokeWidthTextField.setText("1");
                
                prefDashedLinesCheckBox = new JCheckBox("Dashed Lines");
                prefStrokeDashLengthLabel = new JLabel("Stroke Dash Length:");
                prefStrokeDashLengthTextField = new JTextField(5);
                prefStrokeDashLengthTextField.setText("1");
                prefStrokeDashLengthTextField.setEnabled(false);
                
                //all button operations
                upload = new JButton("Upload Preferences from Application");
                load = new JButton("Load Saved Preferences");
                save = new JButton("Save Preferences");
                use = new JButton("Use Preferences");
                
                JPanel prefButtons = new JPanel();
                prefButtons.setLayout(new GridLayout(1, 4));
                
                prefButtons.add(upload);
                prefButtons.add(load);
                prefButtons.add(save);
                prefButtons.add(use);
                
                //handlers
                ButtonHandler buttonHandler = new ButtonHandler();
                CheckBoxHandler checkBoxHandler = new CheckBoxHandler();
                ComboBoxHandler comboBoxHandler = new ComboBoxHandler();
                TextFieldHandler textFieldHandler = new TextFieldHandler();
                
                //apply handlers
                prefColorButton.addActionListener(buttonHandler);
                prefGradientCheckBox.addItemListener(checkBoxHandler);
                prefSecondColorButton.addActionListener(buttonHandler);
                prefShapeComboBox.addItemListener(comboBoxHandler);  
                prefStrokeWidthTextField.addActionListener(textFieldHandler);
                prefDashedLinesCheckBox.addItemListener(checkBoxHandler);
                prefStrokeDashLengthTextField.addActionListener(textFieldHandler);
                
                //add all components
                prefComponents.add(prefColorButton);
                prefComponents.add(prefGradientCheckBox);
                prefComponents.add(prefSecondColorButton);
                prefComponents.add(prefShapeComboBox);
                prefComponents.add(prefFilledCheckBox);
                prefComponents.add(prefStrokeWidthLabel);
                prefComponents.add(prefStrokeWidthTextField);
                prefComponents.add(prefDashedLinesCheckBox);
                prefComponents.add(prefStrokeDashLengthLabel);
                prefComponents.add(prefStrokeDashLengthTextField);
                
                upload.addActionListener(buttonHandler);
                load.addActionListener(buttonHandler);
                save.addActionListener(buttonHandler);
                use.addActionListener(buttonHandler);
                
                prefs.add(prefComponents, BorderLayout.CENTER);
                prefs.add(prefButtons, BorderLayout.SOUTH);
                
                prefs.setDefaultCloseOperation(prefs.DISPOSE_ON_CLOSE);
                prefs.setVisible(true);
            }
            
            //load
            if (event.getSource() == loadImage)
            {
                JFrame loadWindow = new JFrame("Load Image");
                loadWindow.setLayout(new GridLayout(2, 3));
                loadWindow.setSize(800, 200);
                
                JLabel fileNameLabel = new JLabel("Enter Filename (do not use \\, /, :, *, ?, \", <, >, |)");
                JTextField fileName = new JTextField();
                String[] fileEnds = {"png", "gif", "jpg", "bmp"};
                JComboBox fileTypes = new JComboBox(fileEnds);
                JButton loadName = new JButton("load");
                JButton cancel = new JButton("cancel");
                
                //load button handling
                loadName.addActionListener(new ActionListener()
                                               {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            bi = ImageIO.read(new File(fileName.getText() + "." + fileEnds[fileTypes.getSelectedIndex()]));
                            drawPanel.clearDrawing();
                            drawPanel.setBufferedImage(bi);
                            loadedFileName = fileName.getText() + "." + fileEnds[fileTypes.getSelectedIndex()];
                            loadWindow.dispose();
                        }
                        catch (IOException m)
                        {
                            JOptionPane.showMessageDialog(null, "Error: File does not exist");
                        }
                    }
                }
                                           );
                
                //cancel button handling
                cancel.addActionListener(new ActionListener()
                                                 {
                        public void actionPerformed(ActionEvent e)
                        {
                            loadWindow.dispose();
                        }
                    }
                    );
                loadWindow.add(fileNameLabel);
                loadWindow.add(fileName);
                loadWindow.add(fileTypes);
                loadWindow.add(loadName);
                loadWindow.add(cancel);
                
                loadWindow.setDefaultCloseOperation(loadWindow.DISPOSE_ON_CLOSE);
                loadWindow.setVisible(true);
            }
            
            //saving image
            if (event.getSource() == saveImage || event.getSource() == saveAs)
            {
                if (loadedFileName == null || event.getSource() == saveAs)
                {
                    JFrame saveWindow = new JFrame("Save Image");

                    saveWindow.setSize(800, 200);
                    
                    saveWindow.setLayout(new GridLayout(2, 3));
                    
                    JLabel fileNameLabel = new JLabel("Enter Filename (do not use \\, /, :, *, ?, \", <, >, |)");
                    JTextField fileName = new JTextField();
                    String[] fileEnds = {"png", "gif", "jpg", "bmp"};
                    JComboBox fileTypes = new JComboBox(fileEnds);
                    JButton saveName = new JButton("save");
                    JButton cancel = new JButton("cancel");
                    
                    
                    //save button handler
                    saveName.addActionListener(new ActionListener()
                                                   {
                        public void actionPerformed(ActionEvent e)
                        {
                            boolean useable = true;
                            char[] invalid = {'\\', '/', ':', '*', '?', '"', '<', '>', '|'};
                            for (int i = 0; i < 9; i++)
                            {
                                if (fileName.getText().indexOf(invalid[i]) != -1)
                                {
                                    useable = false;
                                    i = 9;
                                }
                            }
                            
                            if (fileName.getText().length() == 0)
                            {
                                useable = false;
                            }
                            
                            if (useable)
                            {
                                try{
                                    bi = createImage(drawPanel);
                                    
                                    File outputFile = new File(fileName.getText() + "." + fileEnds[fileTypes.getSelectedIndex()]);
                                    ImageIO.write(bi, fileEnds[fileTypes.getSelectedIndex()], outputFile);
                                    loadedFileName = fileName.getText() + "." + fileEnds[fileTypes.getSelectedIndex()];
                                    saveWindow.dispose();
                                }
                                catch(IOException p)
                                {
                                    System.out.println("Error idk why");
                                }
                                
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Invalid filename. Please follow the conditions");
                            }
                            
                        }
                    }
                    );
                    
                    //cancel button handler
                    cancel.addActionListener(new ActionListener()
                                                 {
                        public void actionPerformed(ActionEvent e)
                        {
                            saveWindow.dispose();
                        }
                    }
                    );
                    
                    saveWindow.add(fileNameLabel);
                    saveWindow.add(fileName);
                    saveWindow.add(fileTypes);
                    saveWindow.add(saveName);
                    saveWindow.add(cancel);
                    
                    saveWindow.setDefaultCloseOperation(saveWindow.DISPOSE_ON_CLOSE);
                    saveWindow.setVisible(true);
                }
                else{
                    try{
                        
                        bi = createImage(drawPanel);
                        
                        File outputFile = new File(loadedFileName);
                        System.out.println(loadedFileName.substring(loadedFileName.length()-3));
                        ImageIO.write(bi, loadedFileName.substring(loadedFileName.length()-3), outputFile);
                    }
                    catch (IOException m){
                    }
                    
                }
            }
            //exit 
            if (event.getSource() == exit)
            {
                System.exit(0);
            }
            
            if (event.getSource() == undo)
            {
                drawPanel.clearLastShape();
            }
            
            if (event.getSource() == redo)
            {
                drawPanel.redoLastShape();
                
            }
            
        }
    }
    
    //mouse handler for color picker
    private class MouseClickHandler extends MouseAdapter
    {
        //This method takes the event as a parameter and uses ot to pick the color on the screen
        public void mouseReleased(MouseEvent event)
        {
            if (draw == false)
            {
                int xPos = event.getX();
                int yPos = event.getY();
                bi = createImage(drawPanel);
                
                firstColor = new Color(bi.getRGB(xPos, yPos));
                setButtonColors(firstColor, firstColorButton);
                
                colorPicker.setBackground(Color.WHITE);
                draw = true;
                drawPanel.setDraw(true);
            }
            
        }
    }
}