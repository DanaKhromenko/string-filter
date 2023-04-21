package com.gmail.danadiadius.filter.filterPredicate;

import java.util.function.Predicate;

public class StringLengthFilter implements Predicate<String> {
    private static final int MAX_LENGTH = 10;

    @Override
    public boolean test(String value) {
        return value != null
                && value.length() <= MAX_LENGTH;
    }
}
