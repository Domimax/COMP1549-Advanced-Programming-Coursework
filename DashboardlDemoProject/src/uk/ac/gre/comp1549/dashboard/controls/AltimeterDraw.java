package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.Dimension;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.DisplayDraw;

// Display control class for Altimiter which inherits from abstract class DisplayDraw
public class AltimeterDraw extends DisplayDraw {

    /**
     * Default constructor - sets default values
     */
    public AltimeterDraw() {
        this(10, 150, 99999, 0, 0);
    }

    /**
     * @param padding - padding outside the dial
     * @param length - length of the control's line
     * @param maxValue - maximum possible value of the control
     * @param minValue - minimum possible value of the control
     * @param value - current value - where the hand will point
     * Constructor method of AltimiterDraw class to set values
     */
    public AltimeterDraw(int padding, int length, int maxValue, int minValue, int value) {
        this.padding = padding;
        this.value = value;
        this.length = length + 2 * padding;
        this.maxValue = maxValue;
        this.minValue = minValue;
        // set size of the JPanel to be big enough to hold the dial plus padding
        setPreferredSize(new Dimension(this.length, this.length));
    }
}
