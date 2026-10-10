package com.ashis.sqat.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OddEvenDetectorTest {

    private final OddEvenDetector detector = new OddEvenDetector();

    @Test
    void shouldTreatZeroAsEven() {
        assertTrue(detector.isEven(0));
    }

    @Test
    void shouldTreatPositiveEvenNumberAsEven() {
        assertTrue(detector.isEven(42));
    }

    @Test
    void shouldTreatNegativeEvenNumberAsEven() {
        assertTrue(detector.isEven(-42));
    }

    @Test
    void shouldTreatMinimumIntegerAsEven() {
        assertTrue(detector.isEven(Integer.MIN_VALUE));
    }

    @Test
    void shouldTreatPositiveOddNumberAsOdd() {
        assertTrue(detector.isOdd(17));
    }

    @Test
    void shouldTreatNegativeOddNumberAsOdd() {
        assertTrue(detector.isOdd(-17));
    }

    @Test
    void shouldTreatMaximumIntegerAsOdd() {
        assertTrue(detector.isOdd(Integer.MAX_VALUE));
    }

    @Test
    void shouldNotTreatZeroAsOdd() {
        assertFalse(detector.isOdd(0));
    }

    @Test
    void shouldNotTreatOddNumberAsEven() {
        assertFalse(detector.isEven(17));
    }

    @Test
    void shouldNotTreatNegativeOddNumberAsEven() {
        assertFalse(detector.isEven(-17));
    }

    @Test
    void shouldNotTreatEvenNumberAsOdd() {
        assertFalse(detector.isOdd(42));
    }

    @Test
    void shouldNotTreatNegativeEvenNumberAsOdd() {
        assertFalse(detector.isOdd(-42));
    }
}
