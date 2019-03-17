package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GlobalSettings;

import java.util.concurrent.TimeUnit;

public class HelperBase {
  WebDriver wd;
  GlobalSettings gs = new GlobalSettings();

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  public void typeInputField(By locator, String value) {
    if (value != null) {
      WebElement inputField = wd.findElement(locator);
      String currentValue = inputField.getAttribute("value");
      if (!currentValue.equals(value)) {
        inputField.click();
        inputField.clear();
        inputField.sendKeys(value);
      }
    }
  }

  public void selectValue(By locator, String value) {
    new Select(wd.findElement(locator)).selectByVisibleText(value);
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public boolean isElementPresent(By by) {
    try {
      wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
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