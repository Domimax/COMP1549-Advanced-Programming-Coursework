/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard;

import javax.swing.JLabel;
import uk.ac.gre.comp1549.dashboard.controls.Panel;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.ControlDraw;

/**
 *
 * @author ms8794c
 */
public class CommonCode {

    private static CommonCode instance = null;

    private CommonCode() {

    }

    public static synchronized CommonCode getInstance() {
        if (instance == null) {
            instance = new CommonCode();
        }
        return instance;
    }

    public Control createPanel(ControlDraw newObject, String text, int value) {
        Panel<ControlDraw> newPanel = new Panel<>(newObject);
        newPanel.setLabel(text);
        newPanel.setValue(value);
        return newPanel;
    }
}
