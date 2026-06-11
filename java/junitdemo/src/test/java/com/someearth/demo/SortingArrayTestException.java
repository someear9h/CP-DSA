package com.someearth.demo;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class SortingArrayTestException {
    @Test
    void testSortingArray_Exception() {
        // test case should pass if there is exception
        // and fail if there is exception
//        try {
//            SortingArray sortingArray = new SortingArray();
//
//            int[] unsortedArray = {2, 5, 3};
//            int[] sortedArray = sortingArray.sortArray(unsortedArray);
//
//            System.out.println("Statements below exception");
//            fail();
//        }
//        catch(NullPointerException e) {
//            System.out.println("Exception found");
//        }

        SortingArray sortingArray = new SortingArray();
//        int[] unsorted = {2, 5, 3};
        int[] unsorted = null;
        assertThrows(NullPointerException.class, () -> sortingArray.sortArray(unsorted));
    }

    @Test
    void testSortingMethodPerformance() {
        SortingArray array = new SortingArray();
        int[] unsorted = {5, 3, 2};
        assertTimeout(Duration.ofMillis(10), () -> array.sortArray(unsorted));
    }
}
