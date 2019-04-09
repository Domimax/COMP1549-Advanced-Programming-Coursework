package uk.ac.gre.comp1549.dashboard;

import uk.ac.gre.comp1549.dashboard.controls.Panel;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.ControlDraw;

// Singleton class that can contain common methods of the program
public class CommonCode {

    private static CommonCode instance = null;

    private CommonCode() {

    }

    // Synchronized method to avoid multiple instances created by threads
    public static synchronized CommonCode getInstance() {
        if (instance == null) {
            instance = new CommonCode();
        }
        return instance;
    }

    /**
    * @param newObject - control object to set panel for
    * @param text - text for the label of panel
    * @param value - integer to set starting point value for the control object
    * Method to create a common panel for the GUI
    */
    public Control createPanel(ControlDraw newObject, String text, int value) {
        Panel<ControlDraw> newPanel = new Panel<>(newObject);
        newPanel.setLabel(text);
        newPanel.setValue(value);
        return newPanel;
    }
}
