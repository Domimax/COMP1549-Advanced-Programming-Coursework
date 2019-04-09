package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import uk.ac.gre.comp1549.dashboard.controls.abstracts.DialDraw;

/*
* Dial control class for Speedometer which inherits from abstract class DialDraw
* and implements DialAngle interface
*/
public class SpeedometerDraw extends DialDraw implements DialAngle {
    /**
     * Default constructor - sets default values
     */
    public SpeedometerDraw() {
        this(50, 10, 100, 0, 0, 270, -45);
    }

    /**
     * @param radius - radius of the dial
     * @param padding - padding outside the dial
     * @param dialMaxValue - maximum possible value of the control
     * @param dialMinValue - minimum possible value of the control
     * @param value - current value - where the hand will point
     * @param degrees1 - degrees value of the dial
     * @param degrees2 - degrees point where the dial is started to draw
     * Constructor method of SpeedometerDraw class to set values
     */
    public SpeedometerDraw(int radius, int padding, int dialMaxValue, int dialMinValue, int value,  float degrees1, float degrees2) {
        // set size of the JPanel to be big enough to hold the dial plus padding
        setPreferredSize(new Dimension(2 * (radius + padding), 2 * (radius + padding)));
        this.radius = radius;
        this.padding = padding;
        maxValue = dialMaxValue;
        minValue = dialMinValue;
        this.value = value;
        handLength = 0.9 * radius; // hand length is fixed at 90% of radius
        dialExtentDegrees = degrees1;
        dialStartOffsetDegrees = degrees2;
    }

    /**
     * This method is called every time the Dial needs drawing for instance
     * when the value has changed.  It draws the dial itself and the hand in the
     * correct position to indicate the current value
     * @param g - graphics object used to draw on the JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // get a Graphics2D object to draw with
        // draw the hand to indicate the current value
        drawHand(g2, calculateAngle(), handLength);
    }

    /**
     * This method is called every time when the paintComponent is called
     */
    @Override
    public double calculateAngle(){      
        return Math.toRadians(225 - (value * (dialExtentDegrees / maxValue)));
    }  
}
