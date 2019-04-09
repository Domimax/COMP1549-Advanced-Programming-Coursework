package uk.ac.gre.comp1549.dashboard;

import uk.ac.gre.comp1549.dashboard.controls.AltimeterDraw;
import uk.ac.gre.comp1549.dashboard.controls.PetrolVerticalBarDraw;
import uk.ac.gre.comp1549.dashboard.controls.SpeedometerDraw;
import uk.ac.gre.comp1549.dashboard.controls.TachometerDraw;
import uk.ac.gre.comp1549.dashboard.controls.VariometerDraw;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.ControlDraw;

// Factory pattern class to create control types
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
