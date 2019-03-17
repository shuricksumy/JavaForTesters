package ru.stqa.pft.addressbook.model;

public class GlobalSettings {

  private int longWaiterTime = 30;
  private int middleWaiterTime = 10;
  private int quickWaiterTime = 1;

  public int getLongWaiterTime() {
    return longWaiterTime;
  }

  public int getMiddleWaiterTime() {
    return middleWaiterTime;
  }

  public int getQuickWaiterTime() {
    return quickWaiterTime;
  }

  public GlobalSettings() {
  }

}
