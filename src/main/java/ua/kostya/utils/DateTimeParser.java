package ua.kostya.utils;

public class DateTimeParser {
    public static String unquote(String value) {
        StringBuilder builder = new StringBuilder(value);
        builder.deleteCharAt(value.length() - 1);
        builder.deleteCharAt(0);
        return builder.toString();
    }
}
