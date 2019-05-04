package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GlobalSettings;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

@Listeners(MyTestListener.class)
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);
  static GlobalSettings gs = new GlobalSettings();

  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.getSessionHelper().logout();
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start creation " + m.getName() + "with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop creation " + m.getName());
  }


  public void verifyGroupListInUI() {
    if (gs.isVerifyUI()) {
      logger.info("Additional UI Group verification is enabled");
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
          .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
          .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if (gs.isVerifyUI()) {
      logger.info("Additional UI Contact verification is enabled");
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(dbContacts));
    }
  }

}
