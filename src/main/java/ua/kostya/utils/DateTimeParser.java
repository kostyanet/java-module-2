package ua.kostya.utils;

import ua.kostya.data.Date;
import ua.kostya.data.Time;

public class DateTimeParser {

    public static Date parseDate(String date, DateTimeFormat format) {
        String[] parts;
        String year = "", month = "", day = "";

        try {
            if (date.matches(DateTimeFormat.DD_MM_YY_SLASH.getPattern()) &&
                    format == DateTimeFormat.DD_MM_YY_SLASH
            ) {
                parts = date.split("/");
                if (parts.length == 1) {
                    month = parts[0];
                } else if (parts.length == 2) {
                    year = parts[1];
                    month = parts[0];
                } else {
                    year = parts[2];
                    month = parts[1];
                    day = parts[0];
                }

            } else if (date.matches(DateTimeFormat.M_D_YYYY_SLASH.getPattern()) &&
                    format == DateTimeFormat.M_D_YYYY_SLASH
            ) {
                parts = date.split("/");
                if (parts.length == 1) {
                    throw new IllegalArgumentException("Incorrect date value: '" + date + "'!" );
                } else if (parts.length == 2) {
                    day = parts[1];
                    month = parts[0];
                } else {
                    year = parts[2];
                    day = parts[1];
                    month = parts[0];
                }

            } else if (date.matches(DateTimeFormat.DD_MMM_YY_HYPHEN.getPattern()) &&
                    format == DateTimeFormat.DD_MMM_YY_HYPHEN
            ) {
                parts = date.split("-");
                if (parts.length == 1) {
                    year = parts[0];
                } else if (parts.length == 2) {
                    year = parts[1];
                    month = parts[0];
                } else {
                    year = parts[2];
                    month = parts[1];
                    day = parts[0];
                }

            } else if (date.matches(DateTimeFormat.MMM_D_YY_HYPHEN.getPattern()) &&
                    format == DateTimeFormat.MMM_D_YY_HYPHEN
            ) {
                parts = date.split("-");
                if (parts.length == 1) {
                    throw new IllegalArgumentException("Incorrect date value: '" + date + "'!" );
                } else if (parts.length == 2) {
                    year = parts[1];
                    month = parts[0];
                } else {
                    year = parts[2];
                    day = parts[1];
                    month = parts[0];
                }

            } else {
                throw new IllegalArgumentException("Incorrect date value: '" + date + "'!" );
            }

            return new Date(day, month, year);

        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect date value: '" + date + "'!" );
        }
    }

    public static Time parseTime(String time) {
        if (time.equals("")) return new Time("", "", "");

        try {
            String[] parts= time.split(":");

            if (time.matches(DateTimeFormat.HH_MM_SS.getPattern())) {
                return new Time(parts[0], parts[1], parts[2]);

            } else if (time.matches(DateTimeFormat.HH_MM.getPattern())) {
                return new Time(parts[0], parts[1], "");

            } else {
                throw new IllegalArgumentException("Incorrect time value: '" + time + "'!" );
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect time value: '" + time + "'!" );
        }
    }

}
