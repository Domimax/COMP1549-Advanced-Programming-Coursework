/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author ms8794c
 */
public class Altimeter extends JPanel {

    private AltimeterDraw altimeter; // the bar itself
    private JLabel lblTitle; // the label which always appears above the bar

    /**
     * Default constructor
     */
    public Altimeter() {
        setLayout(new BorderLayout());
        // set the style of the border
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        // position the label above the bar 
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        altimeter = new AltimeterDraw();
        add(altimeter, BorderLayout.CENTER);
    }

    /**
     * Set the value for the bar
     *
     * @param value - value for the bar
     */
    public void setValue(int value) {
        altimeter.setValue(value);
    }

    /**
     *
     * @param label - label to appear above the dial
     */
    public void setLabel(String label) {
        lblTitle.setText(label);
    }
}
