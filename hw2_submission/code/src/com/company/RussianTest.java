package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class RussianTest {

    // black box test
    @Test
    public void multiplyBlackBoxTest1() {
        assertEquals(Russian.multiply(1, 1), 1 * 1);

    }

    // black box test
    @Test
    public void multiplyBlackBoxTest2() {
        assertEquals(Russian.multiply(0, 0), 0 * 0);
    }

    // black box test
    // fails for negative numbers
    @Test
    public void multiplyBlackBoxTest3() {
        assertEquals(Russian.multiply(-10000, -10000), -10000 * -10000);
    }

    // black box test
    @Test
    public void multiplyBlackBoxTest4() {
        assertEquals(Russian.multiply(10000, 10000), 10000 * 10000);
    }


    // white box test
    // covers 100%
    @Test
    public void multiplyWhiteBoxTest() {
        assertEquals(Russian.multiply(10, 10), 10 * 10);

    }

     //fault based test
    @Test
    public void multiplyFaultBasedTes1t() {
        assertEquals(Russian.multiply(10, 10), 10 * 10);
    }

    @Test
    public void multiplyFaultBasedTestFualt1() {
        assertNotEquals(RussianFaultTest1.multiply(10, 10), 10 * 10);
    }

    @Test
    public void multiplyFaultBasedTest2() {
        assertEquals(Russian.multiply(10, 10), 10 * 10);
    }

    @Test
    public void multiplyFaultBasedTestFualt2() {
        assertEquals(RussianFaultTest2.multiply(10, 10), 10 * 10);
    }


}