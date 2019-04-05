/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls.abstracts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.gre.comp1549.dashboard.controls.TachometerDraw;

/**
 *
 * @author ms8794c
 */
public class DialDrawTest {

    public DialDrawTest() {
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
     * Test of calculateAngle method, of class DialDraw.
     */
    @Test
    public void testCalculateAngle1() {
        System.out.println("calculateAngle1");
        DialDraw instance = new TachometerDraw();
        instance.setValue(36);
        double expResult = Math.toRadians(115.2);
        double result = instance.calculateAngle();
        assertEquals(result, expResult, 0.0001);
    }
    
    @Test
    public void testCalculateAngle2() {
        System.out.println("calculateAngle2");
        DialDraw instance = new TachometerDraw();
        instance.setValue(0);
        double expResult = Math.toRadians(180);
        double result = instance.calculateAngle();
        assertEquals(result, expResult, 0.0001);
    }
    
    @Test
    public void testCalculateAngle3() {
        System.out.println("calculateAngle3");
        DialDraw instance = new TachometerDraw();
        instance.setValue(100);
        double expResult = Math.toRadians(0);
        double result = instance.calculateAngle();
        assertEquals(result, expResult, 0.0001);
    }
    
    @Test
    public void testCalculateAngle4() {
        System.out.println("calculateAngle4");
        DialDraw instance = new TachometerDraw();
        instance.setValue(90);
        double expResult = Math.toRadians(18);
        double result = instance.calculateAngle();
        assertEquals(result, expResult, 0.0001);
    }
    
    @Test
    public void testCalculateAngle5() {
        System.out.println("calculateAngle5");
        DialDraw instance = new TachometerDraw();
        instance.setValue(50);
        double expResult = Math.toRadians(90);
        double result = instance.calculateAngle();
        assertEquals(result, expResult, 0.0001);
    }
}
