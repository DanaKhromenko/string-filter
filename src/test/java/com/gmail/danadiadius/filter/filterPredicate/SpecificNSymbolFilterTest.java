package com.gmail.danadiadius.filter.filterPredicate;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SpecificNSymbolFilterTest {
    private static final Predicate<String> filter = new SpecificNSymbolFilter();

    @Test
    void test_NLetterMatches_True() {
        assertTrue(filter.test("testing"), "Expected True when the character is inside a word.");
        assertTrue(filter.test("testi"), "Expected True when the character is at the end of a word.");
        assertTrue(filter.test("    i"), "Expected True when preceding characters are spaces.");
        assertTrue(filter.test("    i "), "Expected True when preceding and following characters are spaces.");
    }

    @Test
    void test_NLetterMatches_False() {
        assertFalse(filter.test("testIng"), "Expected False for a capital letter.");
        assertFalse(filter.test("testnig"), "Expected False when the index of the searched character " +
                "is greater than expected.");
        assertFalse(filter.test("tesitng"), "Expected False when the index of the searched character " +
                "is less than expected.");
    }

    @Test
    void test_ShorterString_False() {
        assertFalse(filter.test("iiii"), "Expected False when the length of the string is less than 5.");
    }

    @Test
    void test_EmptyString_False() {
        assertFalse(filter.test(""), "Expected False for an empty string.");
    }

    @Test
    void test_NullString_False() {
        assertFalse(filter.test(null), "Expected False for a null string.");
    }
}
