/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls.abstracts;

import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author ms8794c
 */
public abstract class ControlDraw extends JPanel {

    protected int padding; // padding outside the dial
    protected int maxValue;
    protected int minValue;
    protected int value; // current value - where the hand will point

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    /**
     * Set the value to be displayed on the dial
     *
     * @param value value
     */
    public void setValue(int value) {
        // don't let the value go over the maximum for the dial.  But what about the minimum?
        if (value >= maxValue){
            this.value = maxValue;
        } else if (value <= minValue) {
            this.value = minValue;
        } else {
            this.value = value;
        }
        repaint();
    }
    
    public int getValue(){
        return value;
    }
}
