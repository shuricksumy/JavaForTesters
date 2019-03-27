package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    ContactData contact1 = new ContactData("firstUserName", "middleUserName", "lastUserName",
        "nickUserName", "userTitle", "testCompany", "London, Main str, 33",
        "+123456789", "email@test.com", "https://test.com",
        new DateData("1", "January", "2000"));

    List<ContactData> contactsBeforeTest =  app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(contact1, true);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().goToHomePage();
    List <ContactData> contactsAfterTest =  app.getContactHelper().getContactList();
    Assert.assertEquals(contactsAfterTest.size(),contactsBeforeTest.size() + 1);

    Assert.assertTrue(contactsAfterTest.contains(contact1));
  }

}
