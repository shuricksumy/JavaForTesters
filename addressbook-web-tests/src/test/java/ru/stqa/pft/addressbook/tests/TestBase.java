package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GlobalSettings;

public class TestBase {

  static GlobalSettings gs = new GlobalSettings();

  protected static ApplicationManager app = new ApplicationManager(gs.getBrowser());

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.getSessionHelper().logout();
    app.stop();
  }

}
