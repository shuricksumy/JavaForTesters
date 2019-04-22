package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String userName, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    typeInputField(By.name("username"), userName);
    typeInputField(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    typeInputField(By.name("password"), password);
    typeInputField(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
