package uk.ac.gre.comp1549.dashboard.controls.abstracts;

import java.awt.Graphics;
import javax.swing.*;

// Abstract class for all control types that inherits from JPanel
public abstract class ControlDraw extends JPanel {

    protected int padding; // padding outside the dial
    protected int maxValue; // maximum value of the control
    protected int minValue; // minimum value of the control
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
        // value cannot go below minimum or above maximum
        if (value >= maxValue){
            this.value = maxValue;
        } else if (value <= minValue) {
            this.value = minValue;
        } else {
            this.value = value;
        }
        repaint();
    }
    
    /**
     * Method to get value of the control 
     */
    public int getValue(){
        return value;
    }
    
    /**
     * Method to get maximum value of the control 
     */
    public int getMaxValue() {
        return maxValue;
    }
    
    /**
     * Method to get minimum value of the control 
     */
    public int getMinValue() {
        return minValue;
    }
}
