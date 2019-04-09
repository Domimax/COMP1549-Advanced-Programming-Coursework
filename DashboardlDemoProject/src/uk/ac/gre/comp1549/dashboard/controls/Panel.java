package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import uk.ac.gre.comp1549.dashboard.Control;
import uk.ac.gre.comp1549.dashboard.controls.abstracts.ControlDraw;


public class Panel<T> extends JPanel implements Control {

    private T drawClass;
    private JLabel lblTitle;

    /**
     * Default constructor
     */
    public Panel(T drawClass) {
        this.drawClass = drawClass;
        setLayout(new BorderLayout());
        // set the style of the border
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        // position the label above the bar 
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        add((Component) drawClass, BorderLayout.CENTER);
    }

    /**
     * Set the value for the control
     *
     * @param value - value for the bar
     */
    @Override
    public void setValue(int value) {
        ((ControlDraw) drawClass).setValue(value);
    }

    /**
     * Method to get value of the control 
     */
    @Override
    public int getValue() {
        return ((ControlDraw) drawClass).getValue();
    }

    /**
     * Method to get maximum value of the control 
     */
    @Override
    public int getMaxValue() {
        return ((ControlDraw) drawClass).getMaxValue();
    }

    /**
     * Method to get minimum value of the control 
     */
    @Override
    public int getMinValue() {
        return ((ControlDraw) drawClass).getMinValue();
    }

    /**
     * @param label - label to appear above the dial
     * Method to set text for control's label
     */
    public void setLabel(String label) {
        lblTitle.setText(label);
    }

    /**
     * Method to get control's instance
     */
    public T getDrawClass() {
        return drawClass;
    }
}
