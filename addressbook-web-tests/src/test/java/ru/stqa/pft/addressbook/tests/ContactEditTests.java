package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

import java.util.List;

public class ContactEditTests extends TestBase {

  @Test
  public void testContactEdit() throws Exception {
    ContactData contact1 = new ContactData("firstUserNameEdit", "middleUserNameEdit", "lastUserNameEdit",
        "nickUserNameEdit", "userTitleEdit", "testCompanyEdit", "London, Edit str, 33",
        "+987654321", "email@edit.com", "https://edit.com",
        new DateData("13", "April", "2002"));

    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isAnyContactsExist()) {
      app.getContactHelper().createSimpleContact();
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> contactsBeforeTest = app.getContactHelper().getContactList();
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().initContactEdit();
    app.getContactHelper().fillContactForm(contact1, false);
    app.getContactHelper().submitChanges();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> contactsAfterTest = app.getContactHelper().getContactList();
    Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size());

    Assert.assertTrue(contactsAfterTest.contains(contact1));
  }
}
