package uk.ac.gre.comp1549.dashboard.events;

import java.util.Random;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import uk.ac.gre.comp1549.dashboard.controls.Panel;

// Event class that inherits from Thread
public class ThreadEvent extends Thread {

    private int oldValue; // integer for old control's value
    private int newValue; // integer for new control's value
    private Panel controlObject; // control object to create thread for
    private JButton button; // JButton component to be disabled on the Thread runtime

    /**
     * @param oldValue - integer for old control's value
     * @param newValue - integer for new control's value
     * @param controlObject - control object to create thread for
     * @param button - JButton component to be disabled on the Thread runtime
     * Constructor method for ThreadEvent class
     */
    public ThreadEvent(int oldValue, int newValue, Panel controlObject, JButton button) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.controlObject = controlObject;
        this.button = button;
    }

    /**
     * run() method for Thread object
     */
    @Override
    public void run() {
        // disables JButton component if it was initialized on object creation
        if (button != null) {
            button.setEnabled(false);
        }
        // sets different value for Altimiter display control
        int value = 1;
        if (controlObject.getDrawClass().getClass().getSimpleName().equals("AltimeterDraw")) {
            Random rand = new Random();
            value = (int) ((rand.nextDouble() + 1) * 100);
        }
        /**
         * if control's old value is less than new value, it will run for loop
         * to increment the old value till it is equal to new value
         */
        if (oldValue < newValue) {
            for (int i = oldValue; i <= newValue; i += value) {
                updateValue(i);
                try {
                    Thread.sleep(25);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            value = newValue;
            updateValue(value);
        }
        /**
         * if control's new value is less than old value, it will run for loop
         * to decrement the old value till it is equal to new value
         */
        else {
            for (int i = oldValue; i >= newValue; i -= value) {
                updateValue(i);
                try {
                    Thread.sleep(25);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            value = newValue;
            updateValue(value);
        }
        // enables JButton component if it was initialized on object creation
        if (button != null) {
            button.setEnabled(true);
        }
    }

    /**
     * method to update value of the control
     * @param i - new value of the control
     */
    private void updateValue(final int i) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Here, we can safely update the GUI
                // because we'll be called from the
                // event dispatch thread
                controlObject.setValue(i);
            }
        });
    }
}
