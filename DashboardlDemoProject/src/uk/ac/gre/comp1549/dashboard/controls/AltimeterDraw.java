/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.Dimension;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.DisplayDraw;

/**
 *
 * @author ms8794c
 */
public class AltimeterDraw extends DisplayDraw {

    public AltimeterDraw() {
        this(10, 150, 99999, 0, 0);
    }

    /**
     * @param padding - padding outside the dial
     * @param value - current value - where the hand will point
     */
    public AltimeterDraw(int padding, int length, int maxValue, int minValue, int value) {
        // set size of the JPanel to be big enough to hold the dial plus padding
        this.padding = padding;
        this.value = value;
        this.length = length + 2 * padding;
        this.maxValue = maxValue;
        this.minValue = minValue;
        // set size of the JPanel to be big enough to hold the dial plus padding
        setPreferredSize(new Dimension(this.length, this.length));
    }
}
