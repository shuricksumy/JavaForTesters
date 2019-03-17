package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.CHROME)) {
      System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.SAFARI)) {
      wd = new SafariDriver();
    }
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    wd.manage().window().maximize();
    sessionHelper = new SessionHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
