package ua.kostya.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeParserTest {

    @Test
    void parseTime_success() {
        String[] values =
                {"00:12:45", "00:12", "23:56:00", "23:56", "12:59:59", "12:59", "20:30:30", "20:30"};
        String[] expected =
                {"{h:m:s=00:12:45}", "{h:m:s=00:12:00}", "{h:m:s=23:56:00}", "{h:m:s=23:56:00}", "{h:m:s=12:59:59}", "{h:m:s=12:59:00}", "{h:m:s=20:30:30}", "{h:m:s=20:30:00}"};

        for (int i = 0; i < values.length; i++) {
            assertEquals(
                    expected[i],
                    DateTimeParser.parseTime(values[i]).toString(),
                    values[i]
            );
        }
    }

    @Test
    void parseTime_invalid() {
        String[] values =
                {"23:65:67", "23:65", "01:60:89", "01:89", "25:19", "25:19:23", "25:19:63", "15:19:63"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeParser.parseTime(v);
            });
        }
    }

    @Test
    void parseDate_success() {
        String[] values;
        String[] expected;

        // DD_MM_YY_SLASH
        values = new String[]{"21/12/1234", "1/10/34", "/5/47", "/2/"};
        expected = new String[]{"{d-m-y=21-12-1234}", "{d-m-y=1-10-34}", "{d-m-y=1-5-47}", "{d-m-y=1-1-2}"};

        for (int i = 0; i < values.length; i++) {
            assertEquals(
                    expected[i],
                    DateTimeParser.parseDate(values[i], DateTimeFormat.DD_MM_YY_SLASH).toString(),
                    values[i]
            );
        }

        // M_D_YYYY_SLASH
        values = new String[]{"12/22/1234", "10/1/34", "5/17/", "05/2/"};
        expected = new String[]{"{d-m-y=22-12-1234}", "{d-m-y=1-10-34}", "{d-m-y=17-5-0}", "{d-m-y=2-05-0}"};

        for (int i = 0; i < values.length; i++) {
            assertEquals(
                    expected[i],
                    DateTimeParser.parseDate(values[i], DateTimeFormat.M_D_YYYY_SLASH).toString(),
                    values[i]
            );
        }

        // DD_MMM_YY_HYPHEN
        values = new String[]{"21-May-1234", "1-May-34", "Oct-47", "Jun-7", "1", "43", "1982"};
        expected = new String[]{"{d-m-y=21-May-1234}", "{d-m-y=1-May-34}", "{d-m-y=1-Oct-47}", "{d-m-y=1-Jun-7}", "{d-m-y=1-1-1}", "{d-m-y=1-1-43}", "{d-m-y=1-1-1982}"};

        for (int i = 0; i < values.length; i++) {
            assertEquals(
                    expected[i],
                    DateTimeParser.parseDate(values[i], DateTimeFormat.DD_MMM_YY_HYPHEN).toString(),
                    values[i]
            );
        }

        // MMM_D_YY_HYPHEN
        values = new String[]{"May-21-1234", "May-1-34", "Oct-27", "Jun-7"};
        expected = new String[]{"{d-m-y=21-May-1234}", "{d-m-y=1-May-34}", "{d-m-y=1-Oct-27}", "{d-m-y=1-Jun-7}"};

        for (int i = 0; i < values.length; i++) {
            assertEquals(
                    expected[i],
                    DateTimeParser.parseDate(values[i], DateTimeFormat.MMM_D_YY_HYPHEN).toString(),
                    values[i]
            );
        }
    }

    @Test
    void parseDate_invalid() {
        String[] values;

        // DD_MM_YY_SLASH
        values = new String[]{"21/12/1q34", "32/12/34", "1/13/34", "/15/47", "/5/-47", "/22/"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeParser.parseDate(v, DateTimeFormat.DD_MM_YY_SLASH);
            });
        }

        // M_D_YYYY_SLASH
        values = new String[]{"11/12/1q34", "12/32/34", "13/13/34", "15/17/", "5/-47/", "1/44/"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeParser.parseDate(v, DateTimeFormat.M_D_YYYY_SLASH);
            });
        }

        // DD_MMM_YY_HYPHEN
        values = new String[]{"41-May-1234", "1s-May-34", "Ot-47", "June-67", "12/30/34"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeParser.parseDate(v, DateTimeFormat.DD_MMM_YY_HYPHEN);
            });
        }

        // MMM_D_YY_HYPHEN
        values = new String[]{"May-41-1234", "May-34-34", "Ot-17", "June-67", "12/30/34"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeParser.parseDate(v, DateTimeFormat.MMM_D_YY_HYPHEN);
            });
        }
    }
}
