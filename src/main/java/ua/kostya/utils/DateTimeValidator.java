package ua.kostya.utils;

public class DateTimeValidator {
    public static void validateDate(String dt, DateTimeFormat format) {
        if (dt == null || dt.equals("")) return;

        if (!dt.matches(format.getPattern()))
            throw new IllegalArgumentException("Incorrect date value: '" + dt + "'!" );
    }

    public static void validateTime(String tm, DateTimeFormat format) {
        if (tm == null || tm.equals("")) return;

        if (!tm.matches(format.getPattern()))
            throw new IllegalArgumentException("Incorrect time value: '" + tm + "'!" );
    }
}
