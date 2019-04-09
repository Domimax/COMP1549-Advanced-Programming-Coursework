package uk.ac.gre.comp1549.dashboard.controls.abstracts;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;

// Abstract class for display control types that inherits from ControlDraw
public abstract class DisplayDraw extends ControlDraw {
    
    protected int length; // line length of the display
    
    /**
     * This method is called every time the Display needs drawing for instance
     * when the value has changed.  It draws the display itself and the hand in the
     * correct position to indicate the current value
     * @param g - graphics object used to draw on the JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // get a Graphics2D object to draw with
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, 0));
                 
        Rectangle2D rect = new Rectangle2D.Double(padding, padding, length - padding * 2F, length - padding * 2F);
        g2.draw(rect);
        
        Rectangle2D rect2 = new Rectangle2D.Double(rect.getMinX(), rect.getCenterY() - (rect.getHeight() / 2) / 2, rect.getWidth(), rect.getHeight() / 2);
        g2.draw(rect2);
        
        Font font = new Font("Times New Roman", Font.BOLD, 24);
        
        AttributedString valueString = new AttributedString(value + "");
        valueString.addAttribute(TextAttribute.FONT, font);
        
        g2.drawString(valueString.getIterator(), (float)rect2.getCenterX(), (float)rect2.getCenterY());
    }
}
