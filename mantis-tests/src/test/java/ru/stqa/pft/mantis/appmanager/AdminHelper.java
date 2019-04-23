package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase {


  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public AdminHelper login() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    typeInputField(By.name("username"), app.getProperty("web.adminLogin"));
    typeInputField(By.name("password"), app.getProperty("web.adminPassword"));
    clickByButtonName("Login");
    return this;
  }

  public AdminHelper clickOnText(String linkText) {
    click(By.linkText(linkText));
    return this;
  }

  public AdminHelper openUserDetails(int id) {
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id)));
    return this;
  }

  public AdminHelper resetPassword() {
    clickByButtonName("Reset Password");
    return this;
  }

}
