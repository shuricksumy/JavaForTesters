package ru.stqa.pft.addressbook.model;

public class DateData {
  private String day;
  private String month;
  private String year;

  public String getDay() {
    return day;
  }

  public String getMonth() {
    return month;
  }

  public String getYear() {
    return year;
  }

  public DateData withDay(String day) {
    this.day = day;
    return this;
  }

  public DateData withMonth(String month) {
    this.month = month;
    return this;
  }

  public DateData withYear(String year) {
    this.year = year;
    return this;
  }
}
