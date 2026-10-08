package com.ashis.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeapYearDetectorTest {

    @Test
    void shouldReturnTrueForLeapYear() {

        LeapYearDetector detector = new LeapYearDetector();

        assertTrue(detector.isLeapYear(2024));
    }

    @Test
    void shouldReturnFalseForNonLeapYear() {

        LeapYearDetector detector = new LeapYearDetector();

        assertFalse(detector.isLeapYear(2023));
    }

    @Test
    void shouldReturnFalseForCenturyYear() {

        LeapYearDetector detector = new LeapYearDetector();

        assertFalse(detector.isLeapYear(1900));
    }

    @Test
    void shouldReturnTrueFor400Year() {

        LeapYearDetector detector = new LeapYearDetector();

        assertTrue(detector.isLeapYear(2000));
    }
}