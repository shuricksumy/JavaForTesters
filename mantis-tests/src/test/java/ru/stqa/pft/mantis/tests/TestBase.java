package ru.stqa.pft.mantis.tests;


import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;


public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);
  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php_bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.ftp().restore("config_inc.php_bak", "config_inc.php");
    app.stop();
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  boolean isIssueOpen(int issueId) throws ServiceException, MalformedURLException {
    try {
      return app.soap().getIssue(issueId).isOpened();
    } catch (RemoteException e) {
      logger.info("Issue with ID:" + issueId + " not found. Test will be not skipped.");
      return false;
    }

  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      logger.info("Ignored because of opened issue " + issueId);
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
