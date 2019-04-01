package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactForm() {
    click(By.xpath("(//input[@name='submit'])"));
  }

  public void fillContactForm(ContactData contactData, Boolean isCreationMode) {
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
    if (contactData.getBirthday() != null) {
      setBirthday(contactData.getBirthday());
    }

    if (isCreationMode) {
      //TODO selector group
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void setBirthday(DateData dateData) {
    selectValue(By.name("bday"), dateData.getDay());
    selectValue(By.name("bmonth"), dateData.getMonth());
    typeInputField(By.name("byear"), dateData.getYear());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.cssSelector("input[onclick^=DeleteSel]"));
    wd.switchTo().alert().accept();
    WebDriverWait wait = new WebDriverWait(wd, 10);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.msgbox")));
  }

  public void selectFirstContact() {
    click(By.name("selected[]"));
  }

  public void initContactEdit(int number) {
    wd.findElements(By.cssSelector("a img[title=Edit]")).get(number).click();
  }

  public void submitChanges() {
    click(By.name("update"));
  }

  public boolean isAnyContactsExist() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createSimple() {
    ContactData simpleContact = new ContactData()
        .withFirstName("firstSimpleName")
        .withLastName("lastSimpleName")
        .withAddress("London, Simple str, 1")
        .withMobilePhone("+123456789")
        .withEmailAddress("simple@test.com");

    initContactCreation();
    fillContactForm(simpleContact, true);
    submitContactForm();
    backToHomePage();
  }

  public int getContactCount() {
    return getCounterElementsBy(By.name("selected[]"));
  }

  public List<ContactData> list() {
    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> contactElements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement el : contactElements) {
      int id = Integer.parseInt(el.findElement(By.name("selected[]")).getAttribute("value"));
      String contactLastName = el.findElements(By.cssSelector("td")).get(1).getText();
      String contactFirstName = el.findElements(By.cssSelector("td")).get(2).getText();

      ContactData contact = new ContactData()
          .withFirstName(contactFirstName)
          .withLastName(contactLastName)
          .withId(id);

      contacts.add(contact);
    }
    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
    return contacts;
  }

  public void selectContactByNumber(int number) {
    wd.findElements(By.name("selected[]")).get(number).click();
  }


  public void create(ContactData contact1) {
    initContactCreation();
    fillContactForm(contact1, true);
    submitContactForm();
    backToHomePage();
  }

  public void delete(int index) {
    selectContactByNumber(index);
    deleteSelectedContact();
    backToHomePage();
  }

  public void edit(int index, ContactData contact1) {
    selectContactByNumber(index);
    initContactEdit(index);
    fillContactForm(contact1, false);
    submitChanges();
    backToHomePage();
  }

  public void backToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

}
