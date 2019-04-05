/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verticalbarbean;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author ms8794c
 */
public class VerticalBarBean extends JPanel {

    private int padding;
    private int barLength;
    private int barHeight;
    private int minValue;
    private int maxValue;
    private int currentValue;
    private Color emptyColor;
    private Color fullColor;
    private Color lineColor;

    public VerticalBarBean() {
        this.padding = 20;
        this.barLength= 20;
        this.barHeight= 200;
        this.minValue = 0;
        this.maxValue = 100;
        this.currentValue = 100;
        this.emptyColor = Color.RED;
        this.fullColor = Color.YELLOW;
        this.lineColor = Color.BLACK;
        this.setPreferredSize(new Dimension(barLength + (2 * padding), barHeight + (2 * padding)));
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // get a Graphics2D object to draw with
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw the bar itself.   The first 10% of the bar is red.  The last 30% is yellow.  Between 10% and 30% 
        // the colour graduates from red to yellow.   Check the API documentation for GradientPaint to see
        // how this works.
        Rectangle2D barx = new Rectangle2D.Double(padding, padding, barLength, barHeight);
        GradientPaint redtoyellow = new GradientPaint(0, 0 + (float) barx.getHeight() * 0.6F, this.fullColor, 0, 0 + (float) barx.getHeight() * 0.85F, this.emptyColor);
        g2.setPaint(redtoyellow);
        g2.fill(barx);
        // draw the value indicator to show the current value
        g2.setStroke(new BasicStroke(barHeight / 80, BasicStroke.CAP_SQUARE, 0));
        g2.setPaint(this.lineColor);
        Line2D valueIndicator = new Line2D.Double(padding / 2F, padding + barHeight - (barHeight * currentValue / maxValue), barLength + padding * 1.5F, padding + barHeight - (barHeight * currentValue / maxValue));
        g2.draw(valueIndicator);
    }
    
    /**
     * @return the padding
     */
    public int getPadding() {
        return padding;
    }

    /**
     * @param padding the padding to set
     */
    public void setPadding(int padding) {
        this.padding = padding;
    }

    /**
     * @return the barLength
     */
    public int getBarLength() {
        return barLength;
    }

    /**
     * @param barLength the barLength to set
     */
    public void setBarLength(int barLength) {
        this.barLength = barLength;
    }

    /**
     * @return the barHeight
     */
    public int getBarHeight() {
        return barHeight;
    }

    /**
     * @param barHeight the barHeight to set
     */
    public void setBarHeight(int barHeight) {
        this.barHeight = barHeight;
    }

    /**
     * @return the minValue
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * @param minValue the minValue to set
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * @return the maxValue
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @return the currentValue
     */
    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * @param currentValue the currentValue to set
     */
    public void setCurrentValue(int currentValue) {
        if (currentValue >= maxValue) {
            this.currentValue = maxValue;
        } else if (currentValue <= minValue) {
            this.currentValue = minValue;
        } else {
            this.currentValue = currentValue;
        }
        repaint();
    }

    /**
     * @return the emptyColor
     */
    public Color getEmptyColor() {
        return emptyColor;
    }

    /**
     * @param emptyColor the emptyColor to set
     */
    public void setEmptyColor(Color emptyColor) {
        this.emptyColor = emptyColor;
    }

    /**
     * @return the fullColor
     */
    public Color getFullColor() {
        return fullColor;
    }

    /**
     * @param fullColor the fullColor to set
     */
    public void setFullColor(Color fullColor) {
        this.fullColor = fullColor;
    }

    /**
     * @return the lineColor
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * @param lineColor the lineColor to set
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
}
