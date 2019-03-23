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
public class DashboardDemoMain extends JFrame {

    /**
     * Name of the XML script file - change here if you want to use a different
     * filename
     */
    public static final String XML_SCRIPT = "dashboard_script.xml";

    // fields that appear on the control panel
    private JTextField txtSpeedometerDrawValueInput;
    private JTextField txtPetrolValueInput;
    private JTextField txtAltimeterValueInput;
    private JTextField txtVariometerValueInput;
    private JTextField txtTachometerValueInput;
    private JButton btnScript;

    // fields that appear on the dashboard itself
    private Panel<SpeedometerDraw> speedDial;
    private Panel<VariometerDraw> variometerDial;
    private Panel<PetrolVerticalBarDraw> petrolBar;
    private Panel<AltimeterDraw> altimeterPanel;
    private Panel<TachometerDraw> tachometerDial;

    /**
     * Constructor. Does maybe more work than is good for a constructor.
     */
    public DashboardDemoMain() {
        // Set up the frame for the controller
        setTitle("Dashboard demonstration controller");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the speed Dial
        speedDial = new Panel<>(new SpeedometerDraw());
        speedDial.setLabel("SPEED");
        speedDial.setValue(0);

        // add the tachometer Dial
        tachometerDial = new Panel<>(new TachometerDraw());
        tachometerDial.setLabel("TACHOMETER");
        tachometerDial.setValue(0);

        // add the variometer Dial
        variometerDial = new Panel<>(new VariometerDraw());
        variometerDial.setLabel("VARIOMETER");
        variometerDial.setValue(0);
        
        // add the altimeter Panel
        altimeterPanel = new Panel<>(new AltimeterDraw());
        altimeterPanel.setLabel("ALTIMETER");
        altimeterPanel.setValue(0);

        
        // add the petrol Bar
        petrolBar = new Panel<>(new PetrolVerticalBarDraw());
        petrolBar.setLabel("PETROL");
        petrolBar.setValue(100);
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Speed Value:"));
        txtSpeedometerDrawValueInput = new JTextField("0", 3);
        panel.add(txtSpeedometerDrawValueInput);
        DocumentListener speedListener = new ValueListener(speedDial);
        txtSpeedometerDrawValueInput.getDocument().addDocumentListener(speedListener);

        panel.add(new JLabel("Petrol Value:"));
        txtPetrolValueInput = new JTextField("0", 3);
        panel.add(txtPetrolValueInput);
        DocumentListener petrolListener = new ValueListener(petrolBar);
        txtPetrolValueInput.getDocument().addDocumentListener(petrolListener);

        panel.add(new JLabel("Altimeter Value:"));
        txtAltimeterValueInput = new JTextField("0", 3);
        panel.add(txtAltimeterValueInput);
        DocumentListener altimeterListener = new ValueListener(altimeterPanel);
        txtAltimeterValueInput.getDocument().addDocumentListener(altimeterListener);

        panel.add(new JLabel("Variometer Value:"));
        txtVariometerValueInput = new JTextField("0", 3);
        panel.add(txtVariometerValueInput);
        DocumentListener variometerListener = new ValueListener(variometerDial);
        txtVariometerValueInput.getDocument().addDocumentListener(variometerListener);

        panel.add(new JLabel("Tachometer Value:"));
        txtTachometerValueInput = new JTextField("0", 3);
        panel.add(txtTachometerValueInput);
        DocumentListener tachometerListener = new ValueListener(tachometerDial);
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

        dashboard.add(speedDial);
        dashboard.add(tachometerDial);
        dashboard.add(variometerDial);
        dashboard.add(altimeterPanel);
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

    private class ValueListener implements DocumentListener {

        Panel currentControl;

        public ValueListener(Panel currentControl) {
            this.currentControl = currentControl;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                int value;
                switch (currentControl.drawClass.getClass().getSimpleName()) {
                    case "SpeedometerDraw":
                        value = Integer.parseInt(txtSpeedometerDrawValueInput.getText().trim());
                        speedDial.setValue(value);
                        break;
                    case "TachometerDraw":
                        value = Integer.parseInt(txtTachometerValueInput.getText().trim());
                        tachometerDial.setValue(value);
                        break;
                    case "AltimeterDraw":
                        value = Integer.parseInt(txtAltimeterValueInput.getText().trim());
                        altimeterPanel.setValue(value);
                        break;
                    case "VariometerDraw":
                        value = Integer.parseInt(txtVariometerValueInput.getText().trim());
                        variometerDial.setValue(value);
                        break;
                    case "PetrolVerticalBarDraw":
                        value = Integer.parseInt(txtPetrolValueInput.getText().trim());
                        petrolBar.setValue(value);
                        break;
                }
            } catch (NumberFormatException ex) {
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            insertUpdate(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

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
