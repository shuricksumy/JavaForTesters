package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactForm() {
    click(By.xpath("(//input[@name='submit'])"));
  }

  public void fillContactForm(ContactData contactData) {
    typeInputField(By.name("firstname"), contactData.getFirstName());
    typeInputField(By.name("middlename"), contactData.getMiddleName());
    typeInputField(By.name("lastname"), contactData.getLastName());
    typeInputField(By.name("nickname"), contactData.getNickname());
    typeInputField(By.name("title"), contactData.getTitle());
    typeInputField(By.name("company"), contactData.getCompanyName());
    typeInputField(By.name("address"), contactData.getAddress());
    typeInputField(By.name("mobile"), contactData.getMobilePhone());
    typeInputField(By.name("email"), contactData.getEmailAddress());
    typeInputField(By.name("homepage"), contactData.getHomeSite());
    setBirthday(contactData.getBirthday());
  }

  public void setBirthday(DateData dateData) {
    selectValue(By.name("bday"), dateData.getDay());
    selectValue(By.name("bmonth"), dateData.getMonth());
    typeInputField(By.name("byear"), dateData.getYear());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

}
