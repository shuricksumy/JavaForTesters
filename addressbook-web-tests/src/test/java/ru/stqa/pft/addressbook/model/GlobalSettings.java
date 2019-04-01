package ru.stqa.pft.addressbook.model;

import org.openqa.selenium.remote.BrowserType;

public class GlobalSettings {

  private int defaultWaiterTime = 30;
  private int middleWaiterTime = 10;
  private int quickWaiterTime = 1;
  //Windows, Mac
  private String operationSystem = "Mac";
  //FIREFOX,CHROME,SAFARI
  private String browser = BrowserType.FIREFOX;

  public String getBrowser() {
    return browser;
  }

  public int getDefaultWaiterTime() {
    return defaultWaiterTime;
  }

  public int getMiddleWaiterTime() {
    return middleWaiterTime;
  }

  public int getQuickWaiterTime() {
    return quickWaiterTime;
  }

  public String getOperationSystem() {
    return operationSystem;
  }

  public GlobalSettings() {
  }

}
