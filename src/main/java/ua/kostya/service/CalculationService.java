package ua.kostya.service;

import ua.kostya.data.Date;
import ua.kostya.data.Time;
import ua.kostya.utils.DateTimeConverter;

import java.util.List;
import java.util.Map;

public class CalculationService {
    public static float findDiff(Date date1, Time time1, Date date2, Time time2, String metric) {
        float diff = (float) DateTimeConverter.date2sec(date1) + DateTimeConverter.time2sec(time1) -
                DateTimeConverter.date2sec(date2) - DateTimeConverter.time2sec(time2);

        switch (metric.toLowerCase()) {
            case "s":
                return diff;
            case "min":
                return diff / DateTimeConverter.MIN_AS_SEC;
            case "h":
                return diff / DateTimeConverter.HOUR_AS_SEC;
            case "d":
                return diff / DateTimeConverter.DAY_AS_SEC;
            case "m":
                return diff / DateTimeConverter.MONTH_AS_SEC;
            case "y":
                return diff / DateTimeConverter.YEAR_AS_SEC;
            default:
                throw new IllegalArgumentException("Incorrect diff output metric: '" + metric + "'!");
        }
    }

    public static void sortDates(String direction, List<Map<String, Date>> dates) {
        switch (direction.toLowerCase()) {
            case "a":
                dates.sort(CalculationService::ascendingSorter);
                break;
            case "d":
                dates.sort(CalculationService::descendingSorter);
                break;
            default:
                throw new IllegalArgumentException("Incorrect order direction: '" + direction + "'!");
        }
    }

    private static int descendingSorter(Map<String, Date> m1, Map<String, Date> m2) {
        return Math.round(extractSeconds(m2) - extractSeconds(m1));
    }

    private static int ascendingSorter(Map<String, Date> m1, Map<String, Date> m2) {
        return Math.round(extractSeconds(m1) - extractSeconds(m2));
    }

    private static long extractSeconds(Map<String, Date> map) {
        return DateTimeConverter.date2sec((Date) map.values().toArray()[0]);
    }
}
