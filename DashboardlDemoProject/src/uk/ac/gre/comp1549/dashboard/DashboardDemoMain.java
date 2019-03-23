package uk.ac.gre.comp1549.dashboard;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import uk.ac.gre.comp1549.dashboard.controls.*;
import uk.ac.gre.comp1549.dashboard.events.*;
import uk.ac.gre.comp1549.dashboard.scriptreader.DashboardEventGeneratorFromXML;

/**
 * DashboardDemoMain.java Contains the main method for the Dashboard demo
 * application. It: a) provides the controller screen which allows user input
 * which is passed to the display indicators, b) allows the user to run the XML
 * script which changes indicator values, c) creates the dashboard JFrame and
 * adds display indicators to it.
 *
 * @author COMP1549
 * @version 2.0
 */
public class DashboardDemoMain extends JFrame implements Control {

    /**
     * Name of the XML script file - change here if you want to use a different
     * filename
     */
    public static final String XML_SCRIPT = "dashboard_script.xml";

    // fields that appear on the control panel
    private JTextField txtSpeedValueInput;
    private JTextField txtPetrolValueInput;
    private JTextField txtAltimeterValueInput;
    private JTextField txtVariometerValueInput;
    private JTextField txtTachometerValueInput;
    private JButton btnScript;

    // fields that appear on the dashboard itself
    private Speedometer speedDial;
    private Variometer variometerDial;
    private PetrolVerticalBar petrolBar;
    private Altimeter altimeterPanel;
    private Tachometer tachometerDial;

    /**
     * Constructor. Does maybe more work than is good for a constructor.
     */
    public DashboardDemoMain() {
        // Set up the frame for the controller
        setTitle("Dashboard demonstration controller");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Speed Value:"));
        txtSpeedValueInput = new JTextField("0", 3);
        panel.add(txtSpeedValueInput);
        DocumentListener speedListener = new SpeedValueListener();
        txtSpeedValueInput.getDocument().addDocumentListener(speedListener);
        
        panel.add(new JLabel("Petrol Value:"));
        txtPetrolValueInput = new JTextField("0", 3);
        panel.add(txtPetrolValueInput);
        DocumentListener petrolListener = new PetrolValueListener();
        txtPetrolValueInput.getDocument().addDocumentListener(petrolListener);

        panel.add(new JLabel("Altimeter Value:"));
        txtAltimeterValueInput = new JTextField("0", 3);
        panel.add(txtAltimeterValueInput);
        DocumentListener altimeterListener = new AltimeterValueListener();
        txtAltimeterValueInput.getDocument().addDocumentListener(altimeterListener);
        
        panel.add(new JLabel("Variometer Value:"));
        txtVariometerValueInput = new JTextField("0", 3);
        panel.add(txtVariometerValueInput);
        DocumentListener variometerListener = new VariometerValueListener();
        txtVariometerValueInput.getDocument().addDocumentListener(variometerListener);
        
        panel.add(new JLabel("Tachometer Value:"));
        txtTachometerValueInput = new JTextField("0", 3);
        panel.add(txtTachometerValueInput);
        DocumentListener tachometerListener = new TachometerValueListener();
        txtTachometerValueInput.getDocument().addDocumentListener(tachometerListener);

        btnScript = new JButton("Run XML Script");

        // When the button is read the XML script will be run
        btnScript.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        runXMLScript();
                    }
                }.start();
            }
        });
        panel.add(btnScript);
        add(panel);
        pack();

        setLocationRelativeTo(null); // display in centre of screen
        this.setVisible(true);

        // Set up the dashboard screen        
        JFrame dashboard = new JFrame("Demo dashboard");
        dashboard.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        dashboard.setLayout(new FlowLayout());

        // add the speed Dial
        speedDial = new Speedometer();
        speedDial.setLabel("SPEED");
        speedDial.setValue(0);
        dashboard.add(speedDial);
        
        tachometerDial = new Tachometer();
        tachometerDial.setLabel("TACHOMETER");
        tachometerDial.setValue(0);
        dashboard.add(tachometerDial);

        // add the variometer Dial
        variometerDial = new Variometer();
        variometerDial.setLabel("VARIOMETER");
        variometerDial.setValue(0);
        dashboard.add(variometerDial);

        // add the altimeter Panel
        altimeterPanel = new Altimeter();
        altimeterPanel.setLabel("ALTIMETER");
        altimeterPanel.setValue(0);
        dashboard.add(altimeterPanel);

        // add the petrol Bar
        petrolBar = new PetrolVerticalBar();
        petrolBar.setLabel("PETROL");
        petrolBar.setValue(100);
        dashboard.add(petrolBar);
        dashboard.pack();

        // centre the dashboard frame above the control frame
        Point topLeft = this.getLocationOnScreen(); // top left of control frame (this)
        int hControl = this.getHeight(); // height of control frame (this)
        int wControl = this.getWidth(); // width of control frame (this)
        int hDash = dashboard.getHeight(); // height of dashboard frame 
        int wDash = dashboard.getWidth(); // width of dashboard frame 
        // calculate where top left of the dashboard goes to centre it over the control frame
        Point p2 = new Point((int) topLeft.getX() - (wDash - wControl) / 2, (int) topLeft.getY() - (hDash + hControl));
        dashboard.setLocation(p2);
        dashboard.setVisible(true);
    }

    /**
     * Run the XML script file which generates events for the dashboard
     * indicators
     */
    private void runXMLScript() {
        try {
            DashboardEventGeneratorFromXML dbegXML = new DashboardEventGeneratorFromXML();

            // Register for speed events from the XML script file
            DashBoardEventListener dbelSpeed = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    speedDial.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("speed", dbelSpeed);

            // Register for petrol events from the XML script file
            DashBoardEventListener dbelPetril = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    petrolBar.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("petrol", dbelPetril);
            
            // Register for variometer events from the XML script file
            DashBoardEventListener dbelVariometer = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    variometerDial.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("variometer", dbelVariometer);
            
            // Register for altimeter events from the XML script file
            DashBoardEventListener dbelAltimeter = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    altimeterPanel.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("altimeter", dbelAltimeter);
            
            // Register for tachometer events from the XML script file
            DashBoardEventListener dbelTachometer = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    tachometerDial.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("tachometer", dbelTachometer);

            // Process the script file - it willgenerate events as it runs
            dbegXML.processScriptFile(XML_SCRIPT);

        } catch (Exception ex) {
            Logger.getLogger(DashboardDemoMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Set the speed value to the value entered in the textfield.
     */
    public void setSpeed() {
        try {
            int value = Integer.parseInt(txtSpeedValueInput.getText().trim());
            speedDial.setValue(value);
        } catch (NumberFormatException e) {
        }
        // don't set the speed if the input can't be parsed
    }

    /**
     * Set the petrol value to the value entered in the textfield.
     */
    public void setPetrol() {
        try {
            int value = Integer.parseInt(txtPetrolValueInput.getText().trim());
            petrolBar.setValue(value);
        } catch (NumberFormatException e) {
        }
        // don't set the speed if the input can't be parsed
    }
    
    /**
     * Set the petrol value to the value entered in the textfield.
     */
    public void setAltimeter() {
        try {
            int value = Integer.parseInt(txtAltimeterValueInput.getText().trim());
            altimeterPanel.setValue(value);
        } catch (NumberFormatException e) {
        }
        // don't set the speed if the input can't be parsed
    }
    
    public void setVariometer() {
        try {
            int value = Integer.parseInt(txtVariometerValueInput.getText().trim());
            variometerDial.setValue(value);
        } catch (NumberFormatException e) {
        }
    }
    
    public void setTachometer() {
        try {
            int value = Integer.parseInt(txtTachometerValueInput.getText().trim());
            tachometerDial.setValue(value);
        } catch (NumberFormatException e) {
        }
    }

    /**
     * Respond to user input in the Speed textfield
     */
    private class SpeedValueListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent event) {
            setSpeed();
        }

        @Override
        public void removeUpdate(DocumentEvent event) {
            setSpeed();
        }

        @Override
        public void changedUpdate(DocumentEvent event) {
        }
    }

    /**
     * Respond to user input in the Petrol textfield
     */
    private class PetrolValueListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent event) {
            setPetrol();
        }

        @Override
        public void removeUpdate(DocumentEvent event) {
            setPetrol();
        }

        @Override
        public void changedUpdate(DocumentEvent event) {
        }
    }
    
    /**
     * Respond to user input in the Petrol textfield
     */
    private class AltimeterValueListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent event) {
            setAltimeter();
        }

        @Override
        public void removeUpdate(DocumentEvent event) {
            setAltimeter();
        }

        @Override
        public void changedUpdate(DocumentEvent event) {
        }
    }
    
    private class VariometerValueListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent event) {
            setVariometer();
        }

        @Override
        public void removeUpdate(DocumentEvent event) {
            setVariometer();
        }

        @Override
        public void changedUpdate(DocumentEvent event) {
        }
    }
    
    private class TachometerValueListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent event) {
            setTachometer();
        }

        @Override
        public void removeUpdate(DocumentEvent event) {
            setTachometer();
        }

        @Override
        public void changedUpdate(DocumentEvent event) {
        }
    }

    /**
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        final DashboardDemoMain me = new DashboardDemoMain();
    }
}
