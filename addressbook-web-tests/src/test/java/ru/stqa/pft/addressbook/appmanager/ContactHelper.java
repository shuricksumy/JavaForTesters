package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.DateData;

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
    typeInputField(By.name("home"), contactData.getHomePhone());
    typeInputField(By.name("work"), contactData.getWorkPhone());
    typeInputField(By.name("mobile"), contactData.getMobilePhone());
    typeInputField(By.name("email"), contactData.getEmailAddressFirst());
    typeInputField(By.name("email2"), contactData.getEmailAddressSecond());
    typeInputField(By.name("email3"), contactData.getEmailAddressThird());
    typeInputField(By.name("homepage"), contactData.getHomeSite());
    atach(By.name("photo"), contactData.getPhoto());
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

  public void initContactEdit(int id) {
    wd.findElement(By.cssSelector(String.format("a[href*='edit.php?id=%s']", id))).click();
  }

  public void submitChanges() {
    click(By.name("update"));
  }

  public void createSimple() {
    ContactData simpleContact = new ContactData()
        .withFirstName("firstSimpleName")
        .withLastName("lastSimpleName")
        .withAddress("London, Simple str, 1")
        .withMobilePhone("+123456789")
        .withEmailAddressFirst("simple@test.com");

    initContactCreation();
    fillContactForm(simpleContact, true);
    submitContactForm();
    contactCache = null;
    backToHomePage();
  }

  private Contacts contactCache = null;


  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
    contactCache = new Contacts();
    List<WebElement> contactElements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement el : contactElements) {
      int id = Integer.parseInt(el.findElement(By.name("selected[]")).getAttribute("value"));
      String contactLastName = el.findElements(By.cssSelector("td")).get(1).getText();
      String contactFirstName = el.findElements(By.cssSelector("td")).get(2).getText();
      String allPhones = el.findElements(By.cssSelector("td")).get(5).getText();
      String allEmails = el.findElements(By.cssSelector("td")).get(4).getText();
      String address = el.findElements(By.cssSelector("td")).get(3).getText();

      ContactData contact = new ContactData()
          .withFirstName(contactFirstName)
          .withLastName(contactLastName)
          .withId(id)
          .withAllPhones(allPhones)
          .withEmailAddressAll(allEmails)
          .withAddress(address);

      contactCache.add(contact);
    }
    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
    return contactCache;
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
  }


  public void create(ContactData contact1) {
    initContactCreation();
    fillContactForm(contact1, true);
    submitContactForm();
    contactCache = null;
    backToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    backToHomePage();
  }

  public void edit(ContactData contact) {
    selectContactById(contact.getId());
    initContactEdit(contact.getId());
    fillContactForm(contact, false);
    submitChanges();
    contactCache = null;
    backToHomePage();
  }

  public void backToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  public int count() {
    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
    int size = wd.findElements(By.name("selected[]")).size();
    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
    return size;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactEdit(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
    String title = wd.findElement(By.name("title")).getAttribute("value");
    String companyName = wd.findElement(By.name("company")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String emailAddressFirst = wd.findElement(By.name("email")).getAttribute("value");
    String emailAddressSecond = wd.findElement(By.name("email2")).getAttribute("value");
    String emailAddressThird = wd.findElement(By.name("email3")).getAttribute("value");
    String homeSite = wd.findElement(By.name("homepage")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
        .withId(contact.getId())
        .withFirstName(firstName)
        .withMiddleName(middleName)
        .withLastName(lastName)
        .withNickname(nickname)
        .withTitle(title)
        .withCompanyName(companyName)
        .withAddress(address)
        .withMobilePhone(mobilePhone)
        .withHomePhone(homePhone)
        .withWorkPhone(workPhone)
        .withEmailAddressFirst(emailAddressFirst)
        .withEmailAddressSecond(emailAddressSecond)
        .withEmailAddressThird(emailAddressThird)
        .withHomeSite(homeSite);
  }
}
