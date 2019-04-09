package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.Dimension;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.BarDraw;

// Bar control class for PetrolBar which inherits from abstract class BarDraw
public class PetrolVerticalBarDraw extends BarDraw {
    /**
     * Default constructor - sets default values
     */
    public PetrolVerticalBarDraw() {
        this(20, 200, 20, 100, 0, 0);
    }

    /**
     *
     * @param length - length of the horizontal bar
     * @param height - height of the bar
     * @param padding - padding around the bar
     * @param barMaxValue - maximum possible value of the control
     * @param barMinValue - minimum possible value of the control
     * @param value - current value that will be indicated on the bar
     * Constructor method of AltimiterDraw class to set values
     */
    public PetrolVerticalBarDraw(int length, int height, int padding, int barMaxValue, int barMinValue, int value) {
        // set size of the JPanel to be big enough to hold the bar plus padding
        setPreferredSize(new Dimension(length + (2 * padding), height + (2 * padding)));
        barLength = length;
        barHeight = height;
        this.padding = padding;
        maxValue = barMaxValue;
        minValue = barMinValue;
        this.value = value;
    }
}
