/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard;

import uk.ac.gre.comp1549.dashboard.controls.AltimeterDraw;
import uk.ac.gre.comp1549.dashboard.controls.PetrolVerticalBarDraw;
import uk.ac.gre.comp1549.dashboard.controls.SpeedometerDraw;
import uk.ac.gre.comp1549.dashboard.controls.TachometerDraw;
import uk.ac.gre.comp1549.dashboard.controls.VariometerDraw;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.ControlDraw;

/**
 *
 * @author ms8794c
 */
public class ControlDrawFactory {

    public static ControlDraw createControlDraw(String type) {
        switch (type) {
            case "PetrolVerticalBar":
                return new PetrolVerticalBarDraw();
            case "Altimeter":
                return new AltimeterDraw();
            case "Speedometer":
                return new SpeedometerDraw();
            case "Tachometer":
                return new TachometerDraw();
            case "Variometer":
                return new VariometerDraw();
            default:
                return null;
        }
    }
}
