package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class HelperBase {
  WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  public void typeInputField(By locator, String value) {
    WebElement inputField = wd.findElement(locator);
    inputField.click();
    inputField.clear();
    inputField.sendKeys(value);
  }

  public void selectValue(By locator, String value) {
    new Select(wd.findElement(locator)).selectByVisibleText(value);
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
