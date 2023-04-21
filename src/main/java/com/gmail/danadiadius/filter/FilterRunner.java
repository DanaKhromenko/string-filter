package com.gmail.danadiadius.filter;

import com.gmail.danadiadius.filter.filterPredicate.CapitalFirstLetterFilter;
import com.gmail.danadiadius.filter.filterPredicate.SpecificNSymbolFilter;
import com.gmail.danadiadius.filter.filterPredicate.StringLengthFilter;

import java.util.Arrays;
import java.util.function.Predicate;

public class FilterRunner {
    private Predicate<String>[] availableFilters;

    public FilterRunner() {
        // Default predicates
        availableFilters = new Predicate[]{
                new CapitalFirstLetterFilter(),
                new StringLengthFilter(),
                new SpecificNSymbolFilter()
        };
    }

    public FilterRunner(Predicate<String>[] filters) {
        this.availableFilters = filters;
    }

    public boolean filter(String word, int[] filtersIndexes) {
        // Note: We should return True because there are no filters to apply to the string
        // and the default string satisfies the conditions.
        if (filtersIndexes == null) {
            return true;
        }

        for (int index : filtersIndexes) {
            if (!availableFilters[index - 1].test(word)) {
                return false;
            }
        }
        return true;
    }
}
