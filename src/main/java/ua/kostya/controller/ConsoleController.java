package ua.kostya.controller;

import ua.kostya.utils.DateTimeFormat;
import ua.kostya.utils.DateTimeParser;
import ua.kostya.utils.DateTimeValidator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConsoleController {
    private String[] args;
    private DateTimeFormat format = DateTimeFormat.DD_MM_YY_SLASH;

    private static void showMenu(DateTimeFormat format) {
        System.out.println("Calendar Console. CURRENT FORMAT IS: " + format.getFormat());
        System.out.println("Available commands:");
        System.out.println("`diff ms|s|h|d|m|y '<dateTime1>' '<dateTime2>'` -- find difference between two dates in milliseconds, second, hours, days, months or years.");
        System.out.println("`add '<dateTime>' '<time>'` -- add time to dateTime.");
        System.out.println("`sub '<dateTime>' '<time>'` -- subtract time from dateTime.");
        System.out.println("`sort a '<dateTime1>' ... '<dateTimeN>'` -- sort given dates ascending.");
        System.out.println("`sort d '<dateTime1>' ... '<dateTimeN>'` -- sort given dates descending.");
        System.out.println("`set 1` -> dd/mm/yy HH:mm:ss, `set 2` -> m/d/yyyy HH:mm:ss, `set 3` -> mmm-d-yy HH:mm:ss, `set 4` -> dd-mmm-yyyy HH:mm:ss -- change date format");
        System.out.println("`exit` -- stop the app.");
    }

    public void run() {
        showMenu(format);
        listenConsole();
    }

    private void listenConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                handleInput(reader.readLine());

            // todo
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
        args = Arrays.copyOfRange(parts, 1, parts.length - 1);

        switch (command) {
            case "exit": System.exit(0);
            case "diff": handleDiff(); break;
// todo
//            case "add":
//                break;
//
//            case "sub":
//                break;
//
//            case "sort":
//                break;

            case "set": handleFormat(); break;

            default:
                throw new IllegalArgumentException("Unsupported command.");
        }
    }

    private void handleFormat() {
        switch (args[0]) {
            case "1": format = DateTimeFormat.DD_MM_YY_SLASH; break;
            case "2": format = DateTimeFormat.M_D_YYYY_SLASH; break;
            case "3": format = DateTimeFormat.MMM_D_YY_HYPHEN; break;
            case "4": format = DateTimeFormat.DD_MMM_YY_HYPHEN; break;
            default:
                throw new IllegalArgumentException("Unsupported format.");
        }
    }

    private void handleDiff() {
        String metric = args[0];
        String dateTime1 = DateTimeParser.unquote(args[1]);
        String dateTime2 = DateTimeParser.unquote(args[2]);
        String date1 = dateTime1.split(" ")[0];
        String time1 = dateTime1.split(" ")[1];
        String date2 = dateTime2.split(" ")[0];
        String time2 = dateTime2.split(" ")[1];
        DateTimeValidator.validateDate(date1, format);
        DateTimeValidator.validateDate(date2, format);
        DateTimeValidator.validateTime(time1, format);
        DateTimeValidator.validateTime(time2, format);
        
    }
}
