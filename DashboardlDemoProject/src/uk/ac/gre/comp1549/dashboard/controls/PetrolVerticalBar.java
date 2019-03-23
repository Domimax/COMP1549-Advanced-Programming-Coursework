package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * BarPanel. Container for BarDrawPanel to hold bar and label. If a label is not
 * needed BarDrawPanel an be used on its own
 *
 * @author COMP1549
 * @version 2.0
 */
public class PetrolVerticalBar extends JPanel {

    private PetrolVerticalBarDraw bar; // the bar itself
    private JLabel lblTitle; // the label which always appears above the bar

    /**
     * Default constructor
     */
    public PetrolVerticalBar() {
        setLayout(new BorderLayout());
        // set the style of the border
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        // position the label above the bar 
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        bar = new PetrolVerticalBarDraw();
        add(bar, BorderLayout.CENTER);

    }

    /**
     * Set the value for the bar
     *
     * @param value - value for the bar
     */
    public void setValue(int value) {
        bar.setValue(value);
    }

    /**
     *
     * @param label - label to appear above the dial
     */
    public void setLabel(String label) {
        lblTitle.setText(label);
    }
}
