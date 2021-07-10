package ua.kostya.service;

import ua.kostya.data.Date;
import ua.kostya.utils.DateTimeFormat;
import ua.kostya.utils.DateTimeParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class ConsoleController {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private DateTimeFormat format = DateTimeFormat.DD_MM_YY_SLASH;

    private static void showMenu(DateTimeFormat format) {
        System.out.println("Calendar Console. CURRENT FORMAT IS: " + format.getFormat());
        System.out.println("Available commands:");
        System.out.println("diff s|min|h|d|m|y -- find difference between two dates in second, hours, days, months or years.");
//        System.out.println("`add '<dateTime>' '<dateTime>'` -- sum two dateTime values.");
//        System.out.println("`sub '<dateTime>' '<dateTime>'` -- subtract dateTime from dateTime.");
        System.out.println("sort a -- sort given dates ascending.");
        System.out.println("sort d -- sort given dates descending.");
        System.out.println("set 1 -> dd/mm/yy HH:mm:ss, set 2 -> m/d/yyyy HH:mm:ss, set 3 -> mmm-d-yy HH:mm:ss, set 4 -> dd-mmm-yyyy HH:mm:ss -- change date format");
        System.out.println("exit -- stop the app.");
    }

    public void run() {
        showMenu(format);
        listenConsole();
    }

    private void listenConsole() {
        while (true) {
            try {
                handleInput(reader.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong. Please, try another value.");
            }
        }
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ");
        String command = parts[0].toLowerCase();
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        switch (command) {
            case "exit": System.exit(0);
            case "diff": handleDiff(args[0]); break;
// todo
//            case "add": handleAdd(); break;
//            case "sub": handleSub(); break;

            case "sort": handleSort(args[0]); break;

            case "set": handleFormat(args[0]); break;

            default:
                throw new IllegalArgumentException("Unsupported command.");
        }
    }

    private void handleFormat(String value) {
        switch (value) {
            case "1": format = DateTimeFormat.DD_MM_YY_SLASH; break;
            case "2": format = DateTimeFormat.M_D_YYYY_SLASH; break;
            case "3": format = DateTimeFormat.MMM_D_YY_HYPHEN; break;
            case "4": format = DateTimeFormat.DD_MMM_YY_HYPHEN; break;
            default:
                throw new IllegalArgumentException("Unsupported format.");
        }

        System.out.println("Format changed to: " + format.getFormat());
    }

    private void handleDiff(String metric) {
        String dateTime1 = "";
        String dateTime2 = "";

        try {
            System.out.println("First date time:");
            dateTime1 = reader.readLine();
            System.out.println("Second date time:");
            dateTime2 = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] parts1 = dateTime1.split(" ");
        String[] parts2 = dateTime2.split(" ");
        String time1 = parts1.length == 1 ? "" : parts1[1];
        String time2 = parts2.length == 1 ? "" : parts2[1];

        System.out.println("Difference is: " +
                CalculationService.findDiff(
                        DateTimeParser.parseDate(parts1[0], format),
                        DateTimeParser.parseTime(time1),
                        DateTimeParser.parseDate(parts2[0], format),
                        DateTimeParser.parseTime(time2),
                        metric
                )
        );
    }

    private void handleSort(String direction) {
        String[] dateStringArr = new String[0];
        System.out.println("Specify dates space separated:");

        try {
            dateStringArr = reader.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map<String, Date>> dates = Arrays.stream(dateStringArr)
                .map((d) -> {
                    Map<String, Date> map = new HashMap<>();
                    map.put(d, DateTimeParser.parseDate(d, format));
                    return map;
                })
                .collect(Collectors.toList());

        CalculationService.sortDates(direction, dates);
        System.out.println("Sort result: ");
        dates.forEach((d) -> {
            System.out.println(d.keySet().toArray()[0]);
        });
    }
}
