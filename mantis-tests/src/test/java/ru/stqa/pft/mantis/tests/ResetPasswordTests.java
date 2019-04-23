package ru.stqa.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ResetPasswordTests extends TestBase {

  @BeforeTest
  public void checkUsersWithLocalEmail() throws IOException, MessagingException {
    if (app.db().simpleUsers().stream().filter((u) -> u.getEmail().contains("localhost")).count() == 0) {
      logger.info("There is no user with localhost email -> Creating new one.");
      createSimpleUser();
    }
  }

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {
    UserData user = app.db().simpleUsers().stream().filter((u) -> u.getEmail().contains("localhost")).findAny().get();

    //As admin reset password
    app.admin()
        .login()
        .clickOnText("Manage Users")
        .openUserDetails(user.getId())
        .resetPassword();

    //Get mail Link
    String email = user.getEmail();
    String newPassword = "newPassowrd";

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetPasswordLink = findConfirmationLink(mailMessages, email);
    logger.info("Reset password link is " + resetPasswordLink);

    //set new password
    app.closeWebDriver();
    app.registration().finish(resetPasswordLink, newPassword);

    //check new password
    Assert.assertTrue(app.newSession().login(user.getUserName(), newPassword));

  }


  public void createSimpleUser() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String user = String.format("user%s", now);
    String password = "password";

    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    Assert.assertTrue(app.newSession().login(user, password));
  }

}
