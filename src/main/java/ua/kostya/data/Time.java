package ua.kostya.data;

import lombok.Getter;

@Getter
public class Time {
    private String hour = "";
    private String min = "";
    private String sec = "";

    public Time(String hour, String min, String sec) {
        this.hour = checkValue(hour);
        this.min = checkValue(min);
        this.sec = checkValue(sec);
    }

    private String checkValue(String value) {
        return value.equals("") ? "00" : value;
    }

    @Override
    public String toString() {
        return "{h:m:s=" + hour + ":" + min + ":" + sec + "}";
    }
}
