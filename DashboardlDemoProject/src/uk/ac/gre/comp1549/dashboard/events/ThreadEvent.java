/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.events;

import java.util.Random;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import uk.ac.gre.comp1549.dashboard.controls.Panel;

/**
 *
 * @author lukas
 */
public class ThreadEvent extends Thread {

    private int oldValue;
    private int newValue;
    private Panel controlObject;
    private String name;
    private JButton button;

    public ThreadEvent(int oldValue, int newValue, Panel controlObject, JButton button) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.controlObject = controlObject;
        this.name = controlObject.getDrawClass().getClass().getSimpleName();
        this.button = button;
    }

    public void run() {
        if (button != null) {
            button.setEnabled(false);
        }
        int value = 1;
        if (controlObject.getDrawClass().getClass().getSimpleName().equals("AltimeterDraw")) {
            Random rand = new Random();
            value = (int) ((rand.nextDouble() + 1) * 100);
        }
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
        } else {
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
        if (button != null) {
            button.setEnabled(true);
        }
    }

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

    public String getThreadName() {
        return name;
    }
}
