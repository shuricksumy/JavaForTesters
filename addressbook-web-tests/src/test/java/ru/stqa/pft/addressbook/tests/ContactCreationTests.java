package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

public class ContactCreationTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    login("admin", "secret");
  }

  private void login(String user, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(user);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("firstUserName", "middleUserName", "lastUserName",
        "nickUserName", "userTitle", "testCompany", "London, Main str, 33",
        "+123456789", "email@test.com", "https://test.com",
        new DateData("1", "January", "2000")));
    submitContactForm();
    openHomePage();
    logout();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private void openHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  private void submitContactForm() {
    wd.findElement(By.xpath("(//input[@name='submit'])")).click();
  }

  private void fillContactForm(ContactData contactData) {
    fillInputFieldByName("firstname", contactData.getFirstName());
    fillInputFieldByName("middlename", contactData.getMiddleName());
    fillInputFieldByName("lastname", contactData.getLastName());
    fillInputFieldByName("nickname", contactData.getNickname());
    fillInputFieldByName("title", contactData.getTitle());
    fillInputFieldByName("company", contactData.getCompanyName());
    fillInputFieldByName("address", contactData.getAddress());
    fillInputFieldByName("mobile", contactData.getMobilePhone());
    fillInputFieldByName("email", contactData.getEmailAddress());
    fillInputFieldByName("homepage", contactData.getHomeSite());
    setBirthday(contactData.getBirthday());
  }

  private void fillInputField(WebElement inputField, String value) {
    inputField.clear();
    inputField.sendKeys(value);
  }

  private void fillInputFieldByName(String inputFieldName, String value) {
    WebElement input = wd.findElement(By.name(inputFieldName));
    fillInputField(input, value);
  }

  private void setBirthday(DateData dateData) {
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(dateData.getDay());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(dateData.getMonth());
    fillInputFieldByName("byear",dateData.getYear());
  }

  private void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
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

}
