package ua.kostya.utils;

import lombok.Getter;

@Getter
public enum DateTimeFormat {
    DD_MM_YY_SLASH("dd/mm/yy HH:mm:ss", "^([0-9]|0\\d|1\\d|2\\d|30|31)?/(\\d|0\\d|1[0-2])/\\d{0,4}?$"),
    M_D_YYYY_SLASH("m/d/yyyy HH:mm:ss", "^(\\d|0\\d|1[0-2])/([0-9]|0\\d|1\\d|2\\d|30|31)/\\d{0,4}?$"),
    DD_MMM_YY_HYPHEN("dd-mmm-yyyy HH:mm:ss", "^(([0-9]|0\\d|1\\d|2\\d|30|31)-)?((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-)?\\d{0,4}$"),
    MMM_D_YY_HYPHEN("mmm-d-yy HH:mm:ss", "^(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-(([0-9]|0\\d|1\\d|2\\d|30|31)-)?\\d{0,4}?$"),
    HH_MM_SS("HH:mm:ss", "^([0-1]\\d|2[0-3]):([0-5]\\d):?([0-5]\\d)?$");

    private final String format;
    private final String pattern;

    DateTimeFormat(String format, String pattern) {
        this.format = format;
        this.pattern = pattern;
    }
}
