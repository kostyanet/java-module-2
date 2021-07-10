package ua.kostya.data;

import lombok.Getter;

@Getter
public class Date {
    private String day = "";
    private String month = "";
    private String year = "";

    public Date(String day, String month, String year) {
        this.day = day.equals("") ? "1" : day;
        this.month = month.equals("") ? "1" : month;;
        this.year = year.equals("") ? "0" : year;
    }

    @Override
    public String toString() {
        return "{d-m-y=" + day + "-" + month + "-" + year + "}";
    }

}
