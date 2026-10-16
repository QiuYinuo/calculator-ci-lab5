package com.yourname;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator c = new Calculator();
    @Test
    void addition() {
        assertEquals(4.0, c.eval("2", "+", "2"), 1e-9);
    }

    @Test
    void subtraction() {
        assertEquals(-3.0, c.eval("2", "-", "5"), 1e-9);
    }

    @Test
    void multiplication() {
        assertEquals(12.0, c.eval("3", "*", "4"), 1e-9);
    }

    @Test
    void division() {
        assertEquals(2.0, c.eval("10", "/", "5"), 1e-9);
    }

    @Test
    void zeroNumerator() {
        assertEquals(0.0, c.eval("0", "/", "5"), 1e-9);
    }

    @Test
    void decimals() {
        assertEquals(0.3, c.eval("0.1", "+", "0.2"), 1e-9);
    }

    @Test
    void zeroDivisor() {
        assertThrows(IllegalArgumentException.class,
            () -> c.eval("1", "/", "0"));
    }

    @Test
    void negativeZeroDivisor() {
        assertThrows(IllegalArgumentException.class,
            () -> c.eval("1", "/", "-0.0"));
    }

    @Test
    void blankLeft() {
        assertThrows(IllegalArgumentException.class,
            () -> c.eval(" ", "+", "2"));
    }

    @Test
    void blankRight() {
        assertThrows(IllegalArgumentException.class,
            () -> c.eval("2", "+", ""));
    }

    @Test
    void unknownOperator() {
        assertThrows(IllegalArgumentException.class,
            () -> c.eval("2", "?", "3"));
    }

    @Test
    void nonNumeric() {
        assertThrows(NumberFormatException.class,
            () -> c.eval("abc", "+", "3"));
    }

    @Test
    void nullOperator() {
        assertThrows(IllegalArgumentException.class,
            () -> c.eval("2", null, "3"));
    }

}
