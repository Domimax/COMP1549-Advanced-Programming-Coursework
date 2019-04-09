package uk.ac.gre.comp1549.dashboard.controls.abstracts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

// Abstract class for bar control types that inherits from ControlDraw
public abstract class BarDraw extends ControlDraw{
    protected int barLength; // length/width of the bar
    protected int barHeight; // height of the bar
    
    /**
     * This method is called every time the Bar needs drawing for instance
     * when the value has changed.  It draws the bar itself and the hand in the
     * correct position to indicate the current value
     * @param g - graphics object used to draw on the JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // get a Graphics2D object to draw with
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle2D barx = new Rectangle2D.Double(padding, padding,  barLength, barHeight);
        // GradientPaint to set first 60% of the bar to be yellow and last 15% to be red
        // In the middle there is gradient color
        GradientPaint redtoyellow = new GradientPaint(0, 0 + (float) barx.getHeight() * 0.6F, Color.YELLOW, 0, 0 + (float) barx.getHeight() * 0.85F, Color.RED);
        g2.setPaint(redtoyellow);
        g2.fill(barx);

        // draw the value indicator to show the current value
        g2.setStroke(new BasicStroke(barHeight/80, BasicStroke.CAP_SQUARE, 0));
        g2.setPaint(Color.BLACK);
        Line2D valueIndicator = new Line2D.Double(padding/2F, padding + barHeight - (barHeight * value / maxValue), barLength + padding * 1.5F, padding + barHeight - (barHeight * value / maxValue));
        g2.draw(valueIndicator);
    }
}
