/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialbean;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

/**
 *
 * @author ms8794c
 */
enum DIAL_TYPE {
    SPEEDOMETER, TACHOMETER, VARIOMETER;
}

public class DialBean extends JPanel {

    private int padding;
    private int minValue;
    private int maxValue;
    private int currentValue;
    private int radius;
    private double handLength;
    private float dialExtentDegrees;
    private float dialStartOffsetDegrees;
    private DIAL_TYPE type;

    public DialBean() {
        this.padding = 10;
        this.currentValue = 0;
        this.radius = 50;
        this.handLength = 0.9 * radius;
        this.setType(DIAL_TYPE.SPEEDOMETER);       
        this.setPreferredSize(new Dimension(2 * (getRadius() + padding), 2 * (getRadius() + padding)));
        repaint();
    }

    /**
     * This method is called every time the Dial needs drawing for instance when
     * the value has changed. It draws the dial itself and the hand in the
     * correct position to indicate the current value
     *
     * @param g - graphics object used to draw on the JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // get a Graphics2D object to draw with
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, 0));

        // draw centre of the dial as a small circle of fixed size
        Ellipse2D circle = new Ellipse2D.Double((getRadius() + padding) - 5, (getRadius() + padding) - 5, 10, 10);
        g2.fill(circle);

        // draw the dial itself
        Arc2D arc = new Arc2D.Double(padding, padding, 2 * getRadius(), 2 * getRadius(), getDialStartOffsetDegrees(), getDialExtentDegrees(), Arc2D.Double.OPEN);
        g2.draw(arc);

        // draw the little lines at the start and end of the dial
        drawDialEnd(g2, Math.toRadians(getDialStartOffsetDegrees()));
        drawDialEnd(g2, Math.toRadians(getDialStartOffsetDegrees() + getDialExtentDegrees()));

        drawHand(g2, calculateAngle(), getHandLength());
    }

    private void drawDialEnd(Graphics2D g2, double angle) {
        // calculate endpoint of line furthest from centre of dial
        Point2D outerEnd = new Point2D.Double((getRadius() + padding) + getRadius() * Math.cos(angle),
                (getRadius() + padding) - getRadius() * Math.sin(angle));
        // calculate endpoint of line closest to centre of dial
        Point2D innerEnd = new Point2D.Double((getRadius() + padding) + ((getRadius() + padding) * .8) * Math.cos(angle),
                (getRadius() + padding) - ((getRadius() + padding) * .8) * Math.sin(angle));
        // draw the line
        g2.draw(new Line2D.Double(outerEnd, innerEnd));
    }

    private void drawHand(Graphics2D g2, double angle, double handLength) {
        // calculate the outer end of the hand
        Point2D end = new Point2D.Double((getRadius() + padding) + handLength * Math.cos(angle),
                (getRadius() + padding) - handLength * Math.sin(angle));
        // calculate the centre 
        Point2D center = new Point2D.Double(getRadius() + padding, getRadius() + padding);
        //     Draw the line
        g2.draw(new Line2D.Double(center, end));
    }

    private double calculateAngle() {
        switch (getType()) {
            case SPEEDOMETER:
                return Math.toRadians(225 - (currentValue * (getDialExtentDegrees() / maxValue)));
            case TACHOMETER:
                return Math.toRadians(180 - (currentValue * (getDialExtentDegrees() / maxValue)));
            case VARIOMETER:
                return Math.toRadians(90 + (currentValue * (getDialExtentDegrees() / (-2 * maxValue))));
            default:
                return 0.0;
        }
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
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * @return the handLength
     */
    public double getHandLength() {
        return handLength;
    }

    /**
     * @param handLength the handLength to set
     */
    public void setHandLength(double handLength) {
        this.handLength = handLength;
    }

    /**
     * @return the dialExtentDegrees
     */
    public float getDialExtentDegrees() {
        return dialExtentDegrees;
    }

    /**
     * @param dialExtentDegrees the dialExtentDegrees to set
     */
    public void setDialExtentDegrees(float dialExtentDegrees) {
        this.dialExtentDegrees = dialExtentDegrees;
    }

    /**
     * @return the dialStartOffsetDegrees
     */
    public float getDialStartOffsetDegrees() {
        return dialStartOffsetDegrees;
    }

    /**
     * @param dialStartOffsetDegrees the dialStartOffsetDegrees to set
     */
    public void setDialStartOffsetDegrees(float dialStartOffsetDegrees) {
        this.dialStartOffsetDegrees = dialStartOffsetDegrees;
    }

    /**
     * @return the type
     */
    public DIAL_TYPE getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(DIAL_TYPE type) {
        this.type = type;
        switch (type) {
            case SPEEDOMETER:
                this.minValue = 0;
                this.maxValue = 100;
                this.dialExtentDegrees = 270;
                this.dialStartOffsetDegrees = -45;
                break;
            case TACHOMETER:
                this.minValue = 0;
                this.maxValue = 100;
                this.dialExtentDegrees = 180;
                this.dialStartOffsetDegrees = 0;
                break;
            case VARIOMETER:
                this.minValue = -50;
                this.maxValue = 50;
                this.dialExtentDegrees = 270;
                this.dialStartOffsetDegrees = -45;
                break;
        }
    }
}
