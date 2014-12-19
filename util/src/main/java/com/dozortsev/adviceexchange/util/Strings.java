package com.dozortsev.adviceexchange.util;

public final class Strings {

    public static String f(String msg, Object... values) {
        return String.format(msg, values);
    }

    public static String concat(Object... snippets) {
        StringBuilder builder = new StringBuilder(snippets.length * 10);
        for (Object snippet : snippets) {
            builder.append(snippet);
        }
        return builder.toString();
    }
}