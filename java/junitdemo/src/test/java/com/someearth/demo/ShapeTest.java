package com.someearth.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapeTest {
    @Test
    public void testCalculateSquareArea() {
        Shape square = new Shape();
        assertEquals(25, square.calculateSquareArea(5));
    }

    @Test
    public void testCalculateCircleArea() {
        Shape circle = new Shape();
        assertEquals(27, circle.calculateCircleArea(3),
                "Area calculated is wrong");
    }
}
