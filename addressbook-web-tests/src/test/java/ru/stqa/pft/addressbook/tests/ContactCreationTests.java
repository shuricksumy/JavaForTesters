package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    ContactData contact1 = new ContactData("firstUserName", "middleUserName", "lastUserName",
        "nickUserName", "userTitle", "testCompany", "London, Main str, 33",
        "+123456789", "email@test.com", "https://test.com",
        new DateData("1", "January", "2000"));

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(contact1);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().openHomePage();
  }

}
