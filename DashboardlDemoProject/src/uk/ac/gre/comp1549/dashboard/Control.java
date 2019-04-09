package uk.ac.gre.comp1549.dashboard;

// Interface for generic class Panel
public interface Control {
    void setValue(int value);
    int getValue();
    int getMaxValue();
    int getMinValue();
}
