package ua.kostya.utils;

import ua.kostya.data.Date;
import ua.kostya.data.Time;

import java.util.HashMap;
import java.util.Map;

public class DateTimeConverter {
    public final static int MIN_AS_SEC = 60;
    public final static int HOUR_AS_SEC = 60 *MIN_AS_SEC;
    public final static int DAY_AS_SEC = 24 * HOUR_AS_SEC;
    public final static int MONTH_AS_SEC = 30 * DAY_AS_SEC;
    public final static long YEAR_AS_SEC = 365 * DAY_AS_SEC;

    private final static int LEAP_YEAR_CYCLE = 4;
    private final static int[] MONTHS_LENGTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final static Map<String, Integer> MONTHS_INDEX = new HashMap<>();

    static {
        MONTHS_INDEX.put("Jan", 1);
        MONTHS_INDEX.put("Feb", 2);
        MONTHS_INDEX.put("Mar", 3);
        MONTHS_INDEX.put("Apr", 4);
        MONTHS_INDEX.put("May", 5);
        MONTHS_INDEX.put("Jun", 6);
        MONTHS_INDEX.put("Jul", 7);
        MONTHS_INDEX.put("Aug", 8);
        MONTHS_INDEX.put("Sep", 9);
        MONTHS_INDEX.put("Oct", 10);
        MONTHS_INDEX.put("Nov", 11);
        MONTHS_INDEX.put("Dec", 12);
    }

    private static long days2sec(String days) {
        return Long.parseLong(days) * DAY_AS_SEC;
    }

    private static long months2sec(Integer month) {
        int days = 0;
        for (int i = 1; i <= month; i++) {
            days += MONTHS_LENGTH[i];
        }
        return days * DAY_AS_SEC;
    }

    private static long years2sec(String yearStr) {
        int year = Integer.parseInt(yearStr);
        int leapYearCount = year == 0 ? 0 : Math.floorDiv(year, LEAP_YEAR_CYCLE);
        return year * YEAR_AS_SEC + leapYearCount * DAY_AS_SEC;
    }

    public static long date2sec(Date date) {
        // Month can be both: mm and MMM.
        int month = date.getMonth().length() < 3
                ? Integer.parseInt(date.getMonth())
                : MONTHS_INDEX.get(date.getMonth());

        return DateTimeConverter.days2sec(date.getDay()) +
                DateTimeConverter.months2sec(month) +
                DateTimeConverter.years2sec(date.getYear());
    }

    public static long time2sec(Time time) {
        return Long.parseLong(time.getHour()) * HOUR_AS_SEC +
                Long.parseLong(time.getMin()) * MIN_AS_SEC +
                Long.parseLong(time.getSec());
    }
}
