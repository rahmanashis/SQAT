package com.ashis.sqat.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void shouldAddTwoNumbers() {
        Calculator calculator = new Calculator();

        assertEquals(10, calculator.add(6, 4));
    }

    @Test
    void shouldSubtractTwoNumbers() {
        Calculator calculator = new Calculator();

        assertEquals(2, calculator.subtract(6, 4));
    }

    @Test
    void shouldMultiplyTwoNumbers() {
        Calculator calculator = new Calculator();

        assertEquals(24, calculator.multiply(6, 4));
    }

    @Test
    void shouldReturnTrueForPositiveNumber() {
        Calculator calculator = new Calculator();

        assertTrue(calculator.isPositive(10));
    }

    @Test
    void shouldReturnFalseForNegativeNumber() {
        Calculator calculator = new Calculator();

        assertFalse(calculator.isPositive(-10));
    }
}