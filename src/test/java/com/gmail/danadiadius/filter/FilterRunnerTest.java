package com.gmail.danadiadius.filter;

import com.gmail.danadiadius.filter.filterPredicate.CapitalFirstLetterFilter;
import com.gmail.danadiadius.filter.filterPredicate.SpecificNSymbolFilter;
import com.gmail.danadiadius.filter.filterPredicate.StringLengthFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

class FilterRunnerTest {
    private static final String TEST_STRING = "Test";

    private FilterRunner filterRunner;
    private Predicate<String>[] filters;

    private int[][] filterOrderCombinations = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};

    @BeforeEach
    void setUp() {
        CapitalFirstLetterFilter capitalFirstLetterFilter = mock(CapitalFirstLetterFilter.class);
        StringLengthFilter stringLengthFilter = mock(StringLengthFilter.class);
        SpecificNSymbolFilter specificNSymbolFilter = mock(SpecificNSymbolFilter.class);

        Mockito.when(capitalFirstLetterFilter.test(eq(TEST_STRING))).thenReturn(true);
        Mockito.when(stringLengthFilter.test(eq(TEST_STRING))).thenReturn(true);
        Mockito.when(specificNSymbolFilter.test(eq(TEST_STRING))).thenReturn(true);

        filters = new Predicate[]{
                capitalFirstLetterFilter,
                stringLengthFilter,
                specificNSymbolFilter
        };

        filterRunner = new FilterRunner(filters);
    }

    @Test
    void filter_AllFiltersPassed_True() {
        String errorMessage = "Expected True when all filters return True.";

        Arrays.stream(filterOrderCombinations).forEach(
                filterOrder -> assertTrue(filterRunner.filter(TEST_STRING, filterOrder), errorMessage)
        );
    }

    @ParameterizedTest
    @CsvSource({"0", "1", "2"})
    void filter_OneFilterFailed_False(int filterIndex) {
        String errorMessage = "Expected False when one filter return False.";

        Mockito.when(filters[filterIndex].test(eq(TEST_STRING))).thenReturn(false);

        Arrays.stream(filterOrderCombinations).forEach(
                filterOrder -> assertFalse(filterRunner.filter(TEST_STRING, filterOrder), errorMessage)
        );
    }

    @Test
    void filter_AllFiltersFailed_False() {
        String errorMessage = "Expected False when all filters return False.";

        for (Predicate<String> filter : filters) {
            Mockito.when(filter.test(eq(TEST_STRING))).thenReturn(false);
        }

        Arrays.stream(filterOrderCombinations).forEach(
                filterOrder -> assertFalse(filterRunner.filter(TEST_STRING, filterOrder), errorMessage)
        );
    }

    @Test
    void filter_EmptyFilterOrderArray_True() {
        assertTrue(filterRunner.filter(TEST_STRING, new int[]{}), "Expected True when the " +
                "filter order array is empty.");
    }

    @Test
    void filter_NullFilterOrderArray_True() {
        assertTrue(filterRunner.filter(TEST_STRING, null), "Expected True when the " +
                "filter order array is null.");
    }
}
