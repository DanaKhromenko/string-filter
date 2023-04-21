package com.gmail.danadiadius.filter.filterPredicate;

import java.util.function.Predicate;

public class SpecificNSymbolFilter implements Predicate<String> {
    private static final int SYMBOL_INDEX = 4;
    private static final char SYMBOL = 'i';

    @Override
    public boolean test(String value) {
        return value != null
                && value.length() > SYMBOL_INDEX
                && value.charAt(SYMBOL_INDEX) == SYMBOL;
    }
}
