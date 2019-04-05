/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialbean;

import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 *
 * @author ms8794c
 */
public class DialBeanBeanInfo extends SimpleBeanInfo {
    public PropertyDescriptor[] getPropertyDescriptors(){
        try{
            PropertyDescriptor padding = new PropertyDescriptor("padding",
            DialBean.class, "getPadding", "setPadding");
            PropertyDescriptor minValue = new PropertyDescriptor("minValue",
            DialBean.class, "getMinValue", "setMinValue");
            PropertyDescriptor maxValue = new PropertyDescriptor("maxValue",
            DialBean.class, "getMaxValue", "setMaxValue");
            PropertyDescriptor currentValue = new PropertyDescriptor("currentValue",
            DialBean.class, "getCurrentValue", "setCurrentValue");
            PropertyDescriptor radius = new PropertyDescriptor("radius",
            DialBean.class, "getRadius", "setRadius");
            PropertyDescriptor handLength = new PropertyDescriptor("handLength",
            DialBean.class, "getHandLength", "setHandLength");
            PropertyDescriptor dialExtentDegrees = new PropertyDescriptor("dialExtentDegrees",
            DialBean.class, "getDialExtentDegrees", "setDialExtentDegrees");
            PropertyDescriptor dialStartOffsetDegrees = new PropertyDescriptor("dialStartOffsetDegrees",
            DialBean.class, "getDialStartOffsetDegrees", "setDialStartOffsetDegrees");
            PropertyDescriptor type = new PropertyDescriptor("type",
            DialBean.class, "getType", "setType");         
            PropertyDescriptor[] pds = new PropertyDescriptor[] {padding, minValue,
                maxValue, currentValue, radius, handLength, dialExtentDegrees, dialStartOffsetDegrees, type};
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
                return loadImage("dialbean.jpg");
            case BeanInfo.ICON_COLOR_32x32:
                return loadImage("dialbean.jpg");
            case BeanInfo.ICON_MONO_16x16:
                return loadImage("dialbean_greyscale.jpg");
            case BeanInfo.ICON_MONO_32x32:
                return loadImage("dialbean_greyscale.jpg");
        }
        return null;
    }
}
