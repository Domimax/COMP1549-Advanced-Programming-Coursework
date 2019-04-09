package uk.ac.gre.comp1549.dashboard.controls.abstracts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.gre.comp1549.dashboard.controls.AltimeterDraw;
import uk.ac.gre.comp1549.dashboard.controls.PetrolVerticalBarDraw;
import uk.ac.gre.comp1549.dashboard.controls.SpeedometerDraw;

public class ControlDrawTest {
    
    public ControlDrawTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setValue method, of class ControlDraw.
     */
    @Test
    public void testSetValue1() {
        System.out.println("setValue1");
        int value = -37;
        int expectedResult = 0;
        ControlDraw instance = new SpeedometerDraw();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail;
        assertEquals(instance.getValue(), expectedResult);
    }
    
    /**
     * Test of setValue method, of class ControlDraw.
     */
    @Test
    public void testSetValue2() {
        System.out.println("setValue2");
        int value = 137;
        int expectedResult = 100;
        ControlDraw instance = new SpeedometerDraw();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail;
        assertEquals(instance.getValue(), expectedResult);
    }
    
    /**
     * Test of setValue method, of class ControlDraw.
     */
    @Test
    public void testSetValue3() {
        System.out.println("setValue3");
        int value = -37;
        int expectedResult = 0;
        ControlDraw instance = new AltimeterDraw();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail;
        assertEquals(instance.getValue(), expectedResult);
    }
    
    /**
     * Test of setValue method, of class ControlDraw.
     */
    @Test
    public void testSetValue4() {
        System.out.println("setValue4");
        int value = 1000000;
        int expectedResult = 99999;
        ControlDraw instance = new AltimeterDraw();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail;
        assertEquals(instance.getValue(), expectedResult);
    }
    
    /**
     * Test of setValue method, of class ControlDraw.
     */
    @Test
    public void testSetValue5() {
        System.out.println("setValue5");
        int value = 66;
        int expectedResult = 66;
        ControlDraw instance = new PetrolVerticalBarDraw();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail;
        assertEquals(instance.getValue(), expectedResult);
    }
}
