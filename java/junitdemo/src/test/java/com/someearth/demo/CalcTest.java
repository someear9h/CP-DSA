package com.someearth.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTest {
    @Test
    void test() {
        Calc calc = new Calc();
        int res = calc.divide(10, 5);
        assertEquals(2, res);
    }
}
