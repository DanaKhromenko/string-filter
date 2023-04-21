package com.gmail.danadiadius.filter.filterPredicate;

import java.util.function.Predicate;

public class CapitalFirstLetterFilter implements Predicate<String> {
    @Override
    public boolean test(String value) {
        return value != null
                && value.length() > 0
                && Character.isUpperCase(value.charAt(0));
    }
}
