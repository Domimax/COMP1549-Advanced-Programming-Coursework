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
public class Variometer extends JPanel {
    private VariometerDraw dial;  // the dial itself
    private JLabel lblTitle; // the label which always appears above the dial

    /**
     * Default constructor
     */
    public Variometer() {
        setLayout(new BorderLayout());
        
        // set the style of the border
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        // position the label above the dial
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        dial = new VariometerDraw();
        add(dial, BorderLayout.CENTER);

    }

    /**
     * Set the value for the dial
     * @param value - value for the dial
     */
    public void setValue(int value) {
        dial.setValue(value);
    }

    /**
     *
     * @param label - label to appear above the dial
     */
    public void setLabel(String label) {
        lblTitle.setText(label);
    }
}
