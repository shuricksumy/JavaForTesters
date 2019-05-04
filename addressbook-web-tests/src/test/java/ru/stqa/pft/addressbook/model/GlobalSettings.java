package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GlobalSettings {

  private final Properties properties;
  private int defaultTimeout;
  private int middleTimeout;
  private int quickTimeout;
  private String verifyUI;

  public GlobalSettings() {
    properties = new Properties();
    String target = System.getProperty("target", "local");
    try {
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
      this.defaultTimeout = Integer.parseInt(properties.getProperty("waiter.defaultTime"));
      this.middleTimeout = Integer.parseInt(properties.getProperty("waiter.middleTime"));
      this.quickTimeout = Integer.parseInt(properties.getProperty("waiter.quickTime"));
      this.verifyUI = properties.getProperty("test.verifyUI");
    } catch (IOException ex) {
      this.defaultTimeout = 30;
      this.middleTimeout = 10;
      this.quickTimeout = 5;
      this.verifyUI = "true";
    }
  }

  public int getDefaultWaiterTime() {
    return defaultTimeout;
  }

  public int getMiddleWaiterTime() {
    return middleTimeout;
  }

  public int getQuickWaiterTime() {
    return quickTimeout;
  }

  public boolean isVerifyUI() {
    return verifyUI.toLowerCase().equals("true");
  }

}
