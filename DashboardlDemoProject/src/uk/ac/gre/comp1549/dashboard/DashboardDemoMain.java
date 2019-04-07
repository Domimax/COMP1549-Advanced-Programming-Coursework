package uk.ac.gre.comp1549.dashboard;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
public class DashboardDemoMain extends JFrame implements ActionListener {

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

    private JButton speedometerButton;
    private JButton tachometerButton;
    private JButton variometerButton;
    private JButton altimeterButton;
    private JButton petrolButton;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the speed Dial
        speedDial = (Panel) CommonCode.getInstance().createPanel(ControlDrawFactory.createControlDraw("Speedometer"), "SPEEDOMETER", 0);
        // add the tachometer Dial
        tachometerDial = (Panel) CommonCode.getInstance().createPanel(ControlDrawFactory.createControlDraw("Tachometer"), "TACHOMETER", 0);
        // add the variometer Dial
        variometerDial = (Panel) CommonCode.getInstance().createPanel(ControlDrawFactory.createControlDraw("Variometer"), "VARIOMETER", 0);
        // add the altimeter Panel
        altimeterPanel = (Panel) CommonCode.getInstance().createPanel(ControlDrawFactory.createControlDraw("Altimeter"), "ALTIMETER", 0);
        // add the petrol Bar
        petrolBar = (Panel) CommonCode.getInstance().createPanel(ControlDrawFactory.createControlDraw("PetrolVerticalBar"), "PETROL", 100);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Speed Value:"));
        txtSpeedometerDrawValueInput = new JTextField("0", 3);
        panel.add(txtSpeedometerDrawValueInput);

        panel.add(new JLabel("Petrol Value:"));
        txtPetrolValueInput = new JTextField("0", 3);
        panel.add(txtPetrolValueInput);

        panel.add(new JLabel("Altimeter Value:"));
        txtAltimeterValueInput = new JTextField("0", 3);
        panel.add(txtAltimeterValueInput);

        panel.add(new JLabel("Variometer Value:"));
        txtVariometerValueInput = new JTextField("0", 3);
        panel.add(txtVariometerValueInput);

        panel.add(new JLabel("Tachometer Value:"));
        txtTachometerValueInput = new JTextField("0", 3);
        panel.add(txtTachometerValueInput);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        speedometerButton = new JButton("Run speedometer");
        speedometerButton.addActionListener(this);
        speedometerButton.setActionCommand("speedometer");
        tachometerButton = new JButton("Run tachometer");
        tachometerButton.addActionListener(this);
        tachometerButton.setActionCommand("tachometer");
        variometerButton = new JButton("Run variometer");
        variometerButton.addActionListener(this);
        variometerButton.setActionCommand("variometer");
        altimeterButton = new JButton("Run altimeter");
        altimeterButton.addActionListener(this);
        altimeterButton.setActionCommand("altimeter");
        petrolButton = new JButton("Run petrolmeter");
        petrolButton.addActionListener(this);
        petrolButton.setActionCommand("petrolmeter");
        buttonPanel.add(speedometerButton);
        buttonPanel.add(tachometerButton);
        buttonPanel.add(variometerButton);
        buttonPanel.add(altimeterButton);
        buttonPanel.add(petrolButton);

        btnScript = new JButton("Run XML Script");

        // When the button is read the XML script will be run
        btnScript.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                disableControls();
                new Thread() {
                    public void run() {
                        runXMLScript();
                    }
                }.start();
            }
        });
        panel.add(btnScript);
        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
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
                    threadManagement(speedDial, Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("speed", dbelSpeed);

            // Register for petrol events from the XML script file
            DashBoardEventListener dbelPetril = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    threadManagement(petrolBar, Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("petrol", dbelPetril);

            // Register for variometer events from the XML script file
            DashBoardEventListener dbelVariometer = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    threadManagement(variometerDial, Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("variometer", dbelVariometer);

            // Register for altimeter events from the XML script file
            DashBoardEventListener dbelAltimeter = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    threadManagement(altimeterPanel, Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("altimeter", dbelAltimeter);

            // Register for tachometer events from the XML script file
            DashBoardEventListener dbelTachometer = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    threadManagement(tachometerDial, Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("tachometer", dbelTachometer);

            // Process the script file - it willgenerate events as it runs
            dbegXML.processScriptFile(XML_SCRIPT);
            enableControls();

        } catch (Exception ex) {
            Logger.getLogger(DashboardDemoMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value;
        if ("speedometer".equals(e.getActionCommand())) {
            value = Integer.parseInt(txtSpeedometerDrawValueInput.getText());
            threadManagement(speedDial, value, speedometerButton);
        }
        if ("tachometer".equals(e.getActionCommand())) {
            value = Integer.parseInt(txtTachometerValueInput.getText());
            threadManagement(tachometerDial, value, tachometerButton);
        }
        if ("variometer".equals(e.getActionCommand())) {
            value = Integer.parseInt(txtVariometerValueInput.getText());
            threadManagement(variometerDial, value, variometerButton);
        }
        if ("altimeter".equals(e.getActionCommand())) {
            value = Integer.parseInt(txtAltimeterValueInput.getText());
            threadManagement(altimeterPanel, value, altimeterButton);
        }
        if ("petrolmeter".equals(e.getActionCommand())) {
            value = Integer.parseInt(txtPetrolValueInput.getText());
            threadManagement(petrolBar, value, petrolButton);
        }
    }

    private void threadManagement(Panel control, int value, JButton button) {
        if (value != control.getValue()) {
            if (value > control.getMaxValue()) {
                value = control.getMaxValue();
            }
            if (value < control.getMinValue()) {
                value = control.getMinValue();
            }
            new ThreadEvent(control.getValue(), value, control, button).start();
        }
    }

    private void threadManagement(Panel control, int value) {
        if (value != control.getValue()) {
            if (value > control.getMaxValue()) {
                value = control.getMaxValue();
            }
            if (value < control.getMinValue()) {
                value = control.getMinValue();
            }
            new ThreadEvent(control.getValue(), value, control, null).start();
        }
    }

    private void disableControls() {
        for (Component c : this.getContentPane().getComponents()) {
            for (Component c1 : ((JPanel) c).getComponents()) {
                if (c1 instanceof JButton) {
                    c1.setEnabled(false);
                }
            }
        }
    }
    
    private void enableControls() {
        for (Component c : this.getContentPane().getComponents()) {
            for (Component c1 : ((JPanel) c).getComponents()) {
                if (c1 instanceof JButton) {
                    c1.setEnabled(true);
                }
            }
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
