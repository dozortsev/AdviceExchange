package com.dozortsev.adviceexchange.util;

import java.util.Calendar;
import java.util.Date;

public final class Dates {

    private final static Calendar CALENDAR = Calendar.getInstance();

    public static Date now() {
        return new Date();
    }

    public static Date newDate(int year, int month, int day) {
        CALENDAR.clear();
        CALENDAR.set(year, month, day);
        return CALENDAR.getTime();
    }
}