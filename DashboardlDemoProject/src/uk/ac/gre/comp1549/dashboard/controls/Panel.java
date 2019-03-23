/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import uk.ac.gre.comp1549.dashboard.Control;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.ControlDraw;

/**
 *
 * @author ms8794c
 */
public class Panel<T> extends JPanel implements Control {

    public T drawClass;
    private JLabel lblTitle;

    /**
     * Default constructor
     */
    public Panel(T drawClass) {
        this.drawClass = drawClass;
        setLayout(new BorderLayout());
        // set the style of the border
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        // position the label above the bar 
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        add((Component) drawClass, BorderLayout.CENTER);
    }

    /**
     * Set the value for the bar
     *
     * @param value - value for the bar
     */
    @Override
    public void setValue(int value) {
        ((ControlDraw) drawClass).setValue(value);
    }

    /**
     *
     * @param label - label to appear above the dial
     */
    public void setLabel(String label) {
        lblTitle.setText(label);
    }
}
