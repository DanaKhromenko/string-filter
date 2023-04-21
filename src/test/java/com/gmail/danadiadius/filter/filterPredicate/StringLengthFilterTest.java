package com.gmail.danadiadius.filter.filterPredicate;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringLengthFilterTest {
    private static final Predicate<String> filter = new StringLengthFilter();

    @Test
    void test_LengthEqualsMaximum_True() {
        assertTrue(filter.test("Flapdoodle"), "Expected True when there are 10 characters in the string.");
        assertTrue(filter.test(" Skedaddle"), "Expected True when the untrimmed string has 10 characters.");
        assertTrue(filter.test("Skedaddle "), "Expected True when the untrimmed string has 10 characters.");
        assertTrue(filter.test("          "), "Expected True when there are 10 spaces in the string.");
    }

    @Test
    void test_LengthUnderMaximum_True() {
        assertTrue(filter.test("Gobbledyg"), "Expected True when there are less than 10 characters.");
        assertTrue(filter.test("1"), "Expected True when there are less than 10 characters.");
    }

    @Test
    void test_LengthMoreMaximum_False() {
        assertFalse(filter.test("Shenanigans"), "Expected False when there are more than 10 characters.");
        assertFalse(filter.test(" Flapdoodle"), "Expected False when the untrimmed string has more than " +
                "10 characters.");
        assertFalse(filter.test("Flapdoodle "), "Expected False when the untrimmed string has more than " +
                "10 characters.");
    }

    @Test
    void test_EmptyString_True() {
        assertTrue(filter.test(""), "Expected True when the string is empty.");
    }

    @Test
    void test_NullString_False() {
        assertFalse(filter.test(null), "Expected False when the string is null.");
    }
}
