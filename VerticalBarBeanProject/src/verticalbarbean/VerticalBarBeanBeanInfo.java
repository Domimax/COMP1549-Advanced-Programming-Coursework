/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verticalbarbean;

import java.awt.Image;
import java.beans.*;

/**
 *
 * @author ms8794c
 */
public class VerticalBarBeanBeanInfo extends SimpleBeanInfo {
    
    public PropertyDescriptor[] getPropertyDescriptors(){
        try{
            PropertyDescriptor padding = new PropertyDescriptor("padding",
            VerticalBarBean.class, "getPadding", "setPadding");
            PropertyDescriptor barLength = new PropertyDescriptor("barLength",
            VerticalBarBean.class, "getBarLength", "setBarLength");
            PropertyDescriptor barHeight = new PropertyDescriptor("barHeight",
            VerticalBarBean.class, "getBarHeight", "setBarHeight");
            PropertyDescriptor minValue = new PropertyDescriptor("minValue",
            VerticalBarBean.class, "getMinValue", "setMinValue");
            PropertyDescriptor maxValue = new PropertyDescriptor("maxValue",
            VerticalBarBean.class, "getMaxValue", "setMaxValue");
            PropertyDescriptor currentValue = new PropertyDescriptor("currentValue",
            VerticalBarBean.class, "getCurrentValue", "setCurrentValue");
            PropertyDescriptor emptyColor = new PropertyDescriptor("emptyColor",
            VerticalBarBean.class, "getEmptyColor", "setEmptyColor");
            PropertyDescriptor fullColor = new PropertyDescriptor("fullColor",
            VerticalBarBean.class, "getFullColor", "setFullColor");
            PropertyDescriptor lineColor = new PropertyDescriptor("lineColor",
            VerticalBarBean.class, "getLineColor", "setLineColor");            
            PropertyDescriptor[] pds = new PropertyDescriptor[] {padding, barLength,
                barHeight, minValue, maxValue, currentValue, emptyColor, fullColor,
                lineColor};
            return pds;
            }
        catch (IntrospectionException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Image getIcon(int iconKind) {
        switch(iconKind) {
            case BeanInfo.ICON_COLOR_16x16: 
                return loadImage("verticalbarbean.jpg");
            case BeanInfo.ICON_COLOR_32x32:
                return loadImage("verticalbarbean.jpg");
            case BeanInfo.ICON_MONO_16x16:
                return loadImage("verticalbarbean_greyscale.jpg");
            case BeanInfo.ICON_MONO_32x32:
                return loadImage("verticalbarbean_greyscale.jpg");
        }
        return null;
    }
}
