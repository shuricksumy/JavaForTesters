package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.mantis.model.GlobalSettings;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class HelperBase {
  protected WebDriver wd;
  protected ApplicationManager app;
  GlobalSettings gs = new GlobalSettings();

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
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

  public void atach(By locator, File file) {
    if (file != null) {
      WebElement inputField = wd.findElement(locator);
      inputField.sendKeys(file.getAbsolutePath());
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
      wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
      return true;
    } catch (NoSuchElementException e) {
      wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
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

  public int getCounterElementsBy(By selector) {
    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
    int counter = wd.findElements(selector).size();
    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
    return counter;
  }

  public void clickByButtonName(String buttonName) {
    click(By.cssSelector("input[value='" + buttonName + "']"));
  }
}

