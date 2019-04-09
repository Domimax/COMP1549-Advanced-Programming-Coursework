package uk.ac.gre.comp1549.dashboard.controls.abstracts;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

// Abstract class for dial control types that inherits from ControlDraw
public abstract class DialDraw extends ControlDraw {

    protected int radius; // radius of dial
    protected double handLength; // length of indicator hand
    
    protected float dialExtentDegrees; // degrees value of the dial
    protected float dialStartOffsetDegrees; // degrees point where the dial is started to draw

    /**
     * This method is called every time the Dial needs drawing for instance
     * when the value has changed.  It draws the dial itself and the hand in the
     * correct position to indicate the current value
     * @param g - graphics object used to draw on the JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // get a Graphics2D object to draw with
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, 0));
        
        // draw centre of the dial as a small circle of fixed size
        Ellipse2D circle = new Ellipse2D.Double((radius + padding) - 5, (radius + padding) - 5, 10, 10);
        g2.fill(circle);
        
        // draw the dial itself
        Arc2D arc = new Arc2D.Double(padding, padding, 2 * radius, 2 * radius, dialStartOffsetDegrees, dialExtentDegrees, Arc2D.Double.OPEN);
        g2.draw(arc);
        
        // draw the little lines at the start and end of the dial
        drawDialEnd(g2, Math.toRadians(dialStartOffsetDegrees));
        drawDialEnd(g2, Math.toRadians(dialStartOffsetDegrees + dialExtentDegrees));
    }
    
    /**
     * 
     * @param g2 - graphics object used to draw on the JPanel
     * @param angle - double value for the dial angle
     */
    protected void drawDialEnd(Graphics2D g2, double angle) {
        // calculate endpoint of line furthest from centre of dial
        Point2D outerEnd = new Point2D.Double((radius + padding) + radius * Math.cos(angle),
                (radius + padding) - radius * Math.sin(angle));
        // calculate endpoint of line closest to centre of dial
        Point2D innerEnd = new Point2D.Double((radius + padding) + ((radius + padding) * .8) * Math.cos(angle),
                (radius + padding) - ((radius + padding) * .8) * Math.sin(angle));
        // draw the line
        g2.draw(new Line2D.Double(outerEnd, innerEnd));
    }
    
    /**
     * 
     * @param g2 - graphics object used to draw on the JPanel
     * @param angle - angle of indicator hand
     * @param handLength  - length of indicator hand
     */
    protected void drawHand(Graphics2D g2, double angle, double handLength) {
        // calculate the outer end of the hand
        Point2D end = new Point2D.Double((radius + padding) + handLength * Math.cos(angle),
                (radius + padding) - handLength * Math.sin(angle));
        // calculate the centre 
        Point2D center = new Point2D.Double(radius + padding, radius + padding);
        // Draw the line
        g2.draw(new Line2D.Double(center, end));
    }
    
    /**
     * abstract method to calculate angle for testing purposes
     */
    protected abstract double calculateAngle();
}
