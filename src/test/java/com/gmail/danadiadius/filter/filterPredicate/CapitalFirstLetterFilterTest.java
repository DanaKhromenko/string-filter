package com.gmail.danadiadius.filter.filterPredicate;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CapitalFirstLetterFilterTest {
    private static final Predicate<String> filter = new CapitalFirstLetterFilter();

    @Test
    void test_FirstLetterCapital_True() {
        assertTrue(filter.test("A"), "Expected True when there is one capital letter only.");
        assertTrue(filter.test("Z"), "Expected True when there is one capital letter only.");

        assertTrue(filter.test("Abc"), "Expected True when the first character is capital in a " +
                "multi-character string.");
        assertTrue(filter.test("Zyx"), "Expected True when the first character is capital in a " +
                "multi-character string.");
    }

    @Test
    void test_FirstLetterCapital_False() {
        assertFalse(filter.test("a"), "Expected False when the first and only character is lowercase.");
        assertFalse(filter.test("z"), "Expected False when the first and only character is lowercase.");

        assertFalse(filter.test("aBC"), "Expected False when the first character is lowercase.");
        assertFalse(filter.test("zYX"), "Expected False when the first character is lowercase.");

        assertFalse(filter.test("@AB"), "Expected False when the ASCII code of the first character is " +
                "less than 65.");
        assertFalse(filter.test("[AB"), "Expected False when the ASCII code of the first character is " +
                "greater than 90.");

        assertFalse(filter.test("0AB"), "Expected False when the first character is a number.");
        assertFalse(filter.test("9AB"), "Expected False when the first character is a number.");

        assertFalse(filter.test(" AB"), "Expected False when the first character is a space.");
    }

    @Test
    void test_EmptyString_False() {
        assertFalse(filter.test(""), "Expected False when the string is empty.");
    }

    @Test
    void test_NullString_False() {
        assertFalse(filter.test(null), "Expected False when the string is null.");
    }
}
