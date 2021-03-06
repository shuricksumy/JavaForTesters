package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void homePage() {
    //Page marker checking before navigation
    if (!isElementPresent(By.name("MainForm"))) {
      click(By.linkText("home"));
    }
  }

  public void groupPage() {
    //Page marker checking before navigation
    if (!isElementPresent(By.cssSelector("input[value*='New group']"))) {
      click(By.linkText("groups"));
    }
  }

}
