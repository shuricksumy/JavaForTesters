package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.DateData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactEditTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().createSimple();
    }
  }

  @Test
  public void testContactEdit() throws Exception {
    Contacts contactsBeforeTest = app.contact().all();
    ContactData modifiedContact = contactsBeforeTest.iterator().next();

    ContactData contact = new ContactData()
        .withFirstName("firstUserNameEdit")
        .withMiddleName("middleUserNameEdit")
        .withLastName("lastUserNameEdit")
        .withNickname("nickUserNameEdit")
        .withTitle("userTitleEdit")
        .withCompanyName("testCompanyEdit")
        .withAddress("London, Edit str, 33")
        .withMobilePhone("+987654321")
        .withEmailAddressFirst("email@edit.com")
        .withHomeSite("https://edit.com")
        .withBirthday(new DateData()
            .withDay("13")
            .withMonth("April")
            .withYear("2002"))
        .withId(modifiedContact.getId());

    app.contact().edit(contact);

    Assert.assertEquals(app.contact().count(), contactsBeforeTest.size());

    Contacts contactsAfterTest = app.contact().all();
    contactsBeforeTest.remove(modifiedContact);
    contactsBeforeTest.add(contact);
    assertEquals(contactsBeforeTest, contactsAfterTest);
    assertThat(contactsAfterTest, equalTo(contactsBeforeTest.without(modifiedContact).withAdded(contact)));
  }
}
