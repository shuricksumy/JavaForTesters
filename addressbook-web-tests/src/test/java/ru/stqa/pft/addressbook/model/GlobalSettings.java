package ru.stqa.pft.addressbook.model;

public class GlobalSettings {

  private int defaultWaiterTime = 30;
  private int middleWaiterTime = 10;
  private int quickWaiterTime = 1;

  public int getDefaultWaiterTime() {
    return defaultWaiterTime;
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
