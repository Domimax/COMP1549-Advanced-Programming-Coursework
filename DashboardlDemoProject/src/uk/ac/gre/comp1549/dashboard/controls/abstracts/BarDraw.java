/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls.abstracts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author ms8794c
 */
public abstract class BarDraw extends ControlDraw{
    protected int barLength; // length/width of the bar
    protected int barHeight; // height of the bar
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // get a Graphics2D object to draw with
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the bar itself.   The first 10% of the bar is red.  The last 30% is yellow.  Between 10% and 30% 
        // the colour graduates from red to yellow.   Check the API documentation for GradientPaint to see
        // how this works.
        Rectangle2D barx = new Rectangle2D.Double(padding, padding,  barLength, barHeight);
        GradientPaint redtoyellow = new GradientPaint(0, 0 + (float) barx.getHeight() * 0.6F, Color.YELLOW, 0, 0 + (float) barx.getHeight() * 0.85F, Color.RED);
        g2.setPaint(redtoyellow);
        g2.fill(barx);

        // draw the value indicator to show the current value
        g2.setStroke(new BasicStroke(barHeight/80, BasicStroke.CAP_SQUARE, 0));
        g2.setPaint(Color.BLACK);
        Line2D valueIndicator = new Line2D.Double(padding/2F, padding + barHeight - (barHeight * value / maxValue), barLength + padding * 1.5F, padding + barHeight - (barHeight * value / maxValue));
        //Line2D valueIndicator = new Line2D.Double(10, 110, 60, 110);
        g2.draw(valueIndicator);
    }
}
