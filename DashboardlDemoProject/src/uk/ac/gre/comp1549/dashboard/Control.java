/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard;

/**
 *
 * @author ms8794c
 */
public interface Control {
    void setValue(int value);
    int getValue();
    int getMaxValue();
    int getMinValue();
}
