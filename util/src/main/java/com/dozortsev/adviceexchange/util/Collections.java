package com.dozortsev.adviceexchange.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public final class Collections {

    public static <T> ArrayList<T> newArrayList(T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}