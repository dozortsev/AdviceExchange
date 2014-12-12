package com.dozortsev.adviceexchange.util;

public final class Strings {

    public static String f(String msg, Object... values) {
        return String.format(msg, values);
    }
}