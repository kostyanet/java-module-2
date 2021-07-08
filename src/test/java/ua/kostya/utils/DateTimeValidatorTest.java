package ua.kostya.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeValidatorTest {

    @Test
    void validateTime_valid() {
        String[] values =
                {"00:12:45", "00:12", "23:56:00", "23:56", "12:59:59", "12:59", "20:30:30", "20:30"};

        for (String v : values) {
            assertDoesNotThrow(() -> {
                DateTimeValidator.validateTime(v, DateTimeFormat.HH_MM_SS);
            });
        }
    }

    @Test
    void validateTime_invalid() {
        String[] values =
                {"23:65:67", "23:65", "01:60:89", "01:89", "25:19", "25:19:23", "25:19:63", "15:19:63"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeValidator.validateTime(v, DateTimeFormat.HH_MM_SS);
            });
        }
    }

    // DateTimeFormat.DD_MM_YY_SLASH
    @Test
    void validateDate_DD_MM_YY_SLASH_valid() {
        String[] values = {"21/12/1234", "1/10/34", "/5/47", "/2/"};

        for (String v : values) {
            assertDoesNotThrow(() -> {
                DateTimeValidator.validateDate(v, DateTimeFormat.DD_MM_YY_SLASH);
            });
        }
    }

    @Test
    void validateDate_DD_MM_YY_SLASH_invalid() {
        String[] values = {"21/12/1q34", "32/12/34", "1/13/34", "/15/47", "/5/-47", "/22/"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeValidator.validateDate(v, DateTimeFormat.DD_MM_YY_SLASH);
            });
        }
    }

    // DateTimeFormat.M_D_YYYY_SLASH
    @Test
    void validateDate_M_D_YYYY_SLASH_valid() {
        String[] values = {"12/22/1234", "10/1/34", "5/17/", "05/2/"};

        for (String v : values) {
            assertDoesNotThrow(() -> {
                DateTimeValidator.validateDate(v, DateTimeFormat.M_D_YYYY_SLASH);
            });
        }
    }

    @Test
    void validateDate_M_D_YYYY_SLASH_invalid() {
        String[] values = {"11/12/1q34", "12/32/34", "13/13/34", "15/17/", "5/-47/", "1/44/"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeValidator.validateDate(v, DateTimeFormat.M_D_YYYY_SLASH);
            });
        }
    }

    // DateTimeFormat.DD_MMM_YY_HYPHEN
    @Test
    void validateDate_DD_MMM_YY_HYPHEN_valid() {
        String[] values = {"21-May-1234", "1-May-34", "Oct-47", "Jun-7", "1", "43", "234", "1982"};

        for (String v : values) {
            assertDoesNotThrow(() -> {
                DateTimeValidator.validateDate(v, DateTimeFormat.DD_MMM_YY_HYPHEN);
            });
        }
    }

    @Test
    void validateDate_DD_MMM_YY_HYPHEN_invalid() {
        String[] values = {"41-May-1234", "1s-May-34", "Ot-47", "June-67", "12/30/34"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeValidator.validateDate(v, DateTimeFormat.DD_MMM_YY_HYPHEN);
            });
        }
    }

    // DateTimeFormat.MMM_D_YY_HYPHEN
    @Test
    void validateDate_MMM_D_YY_HYPHEN_valid() {
        String[] values = {"May-21-1234", "May-1-34", "Oct-27", "Jun-7"};

        for (String v : values) {
            assertDoesNotThrow(() -> {
                DateTimeValidator.validateDate(v, DateTimeFormat.MMM_D_YY_HYPHEN);
            });
        }
    }

    @Test
    void validateDate_MMM_D_YY_HYPHEN_invalid() {
        String[] values = {"May-41-1234", "May-34-34", "Ot-17", "June-67", "12/30/34"};

        for (String v : values) {
            assertThrows(IllegalArgumentException.class, () -> {
                DateTimeValidator.validateDate(v, DateTimeFormat.MMM_D_YY_HYPHEN);
            });
        }
    }
}
