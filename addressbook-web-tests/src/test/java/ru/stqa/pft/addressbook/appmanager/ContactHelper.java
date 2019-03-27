package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;
import ru.stqa.pft.addressbook.model.GroupData;

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

    if (isCreationMode){
      //TODO selector group
    }else{
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
  }

  public void selectFirstContact() {
    click(By.name("selected[]"));
  }

  public void initContactEdit() {
    click(By.cssSelector("a img[title=Edit]"));
  }

  public void submitChanges() {
    click(By.name("update"));
  }

  public boolean isAnyContactsExist(){
    return isElementPresent(By.name("selected[]"));
  }

  public void createSimpleContact() {
    ContactData simpleContact = new ContactData("firstSimpleName", null, "lastSimpleName",
        null, null, null, "London, Simple str, 1",
        "+123456789", "simple@test.com", null,
        null);
    initContactCreation();
    fillContactForm(simpleContact,true);
    submitContactForm();
  }

  public int getContactCount() {
    return getCounterElementsBy(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
      List<ContactData> contacts = new ArrayList<ContactData>();
      List<WebElement> contactElements = wd.findElements(By.cssSelector("tr[name='entry']"));
      for (WebElement el : contactElements) {
        String contactLastName = el.findElements(By.cssSelector("td")).get(1).getText();
        String contactFirstName = el.findElements(By.cssSelector("td")).get(2).getText();
        String contactAddress = el.findElements(By.cssSelector("td")).get(3).getText();
        String contactEnmail = el.findElements(By.cssSelector("td")).get(4).getText();
        String contactPhone = el.findElements(By.cssSelector("td")).get(5).getText();

        ContactData contact = new ContactData(contactFirstName,null,contactLastName,
            null,null,null,contactAddress,contactPhone,contactEnmail,
            null,null);
        contacts.add(contact);
      }
    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
      return contacts;
    }
}
