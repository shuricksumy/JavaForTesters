package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import ru.stqa.pft.addressbook.model.GlobalSettings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private WebDriver wd;
  private final Properties properties;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private DbHelper dbHelper;
  private String browser;
  private GlobalSettings gs = new GlobalSettings();


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    //Start before browser to save time in case db connection issue
    dbHelper = new DbHelper();

    if ("".equals(properties.getProperty("selenium.server"))) {

      if (browser.equals(BrowserType.CHROME)) {
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driverPath"));
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.FIREFOX)) {
        System.setProperty("webdriver.gecko.driver", properties.getProperty("firefox.driverPath"));
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.SAFARI)) {
        wd = new SafariDriver();
      }
    }else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "MAC")));
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }

    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    wd.manage().window().maximize();
    sessionHelper = new SessionHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }
}
