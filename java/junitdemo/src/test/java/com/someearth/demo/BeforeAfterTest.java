package com.someearth.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeforeAfterTest {

    // BeforeAfterTest()-> now why don't we make constructor then?
    // because constructor will be called at every @Test annotation method.
    // which is what we may not want
    // but @beforeAll only is called once for full class of tests
    BeforeAfterTest() {
        System.out.println("Does constructor work here?");
    }

    Shape shape;

    @BeforeAll
    static void beforeAll() {
        System.out.println("execute Before all the testcases");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("execute AFTER all the testcases are done");
    }

    @BeforeEach
    void init() {
        shape = new Shape();
        System.out.println("Before Each test");
    }

    @AfterEach
    void closeResources() {
        // usually used to clean up/destroy resources opened
        // after every test case executed
        System.out.println("CLEAN UP");
    }

    @Test
    public void testCalculateSquareArea() {
        assertEquals(25, shape.calculateSquareArea(5));
        System.out.println("Running actual test");
    }

    @Test
    public void testCalculateCircleArea() {
        assertEquals(27, shape.calculateCircleArea(3),
                "Area calculated is wrong");
        System.out.println("Running actual test");
    }
}