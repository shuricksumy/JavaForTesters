package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import ru.stqa.pft.mantis.model.GlobalSettings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private WebDriver wd;
  private final Properties properties;
  private String browser;
  private GlobalSettings gs = new GlobalSettings();
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public WebDriver getDriver() {
    if (wd == null) {
      if (browser.equals(BrowserType.CHROME)) {
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driverPath"));
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.FIREFOX)) {
        System.setProperty("webdriver.gecko.driver", properties.getProperty("firefox.driverPath"));
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.SAFARI)) {
        wd = new SafariDriver();
      }

      wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
      wd.manage().window().maximize();
    }
    return wd;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }
}
