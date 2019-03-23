package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.Dimension;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.BarDraw;

/**
 * BarDrawPanel.  Draw a horizontal bar indicator and show its current value
 *
 * @author COMP1549
 * @version 2.0
 */
public class PetrolVerticalBarDraw extends BarDraw {

    /**
     * Default constructor - sets default values
     */
    public PetrolVerticalBarDraw() {
        this(20, 200, 20, 100, 0);
    }

    /**
     *
     * @param length - length of the horizontal bar
     * @param height - height of the bar
     * @param padding - padding around the bar
     * @param barMaxValue - bar runs from 0 to barMaxValue
     * @param value - current value that will be indicated on the bar
     */
    public PetrolVerticalBarDraw(int length, int height, int padding, int barMaxValue, int value) {
        // set size of the JPanel to be big enough to hold the bar plus padding
        setPreferredSize(new Dimension(length + (2 * padding), height + (2 * padding)));
        barLength = length;
        barHeight = height;
        this.padding = padding;
        maxValue = barMaxValue;
        this.value = value;
    }
}
