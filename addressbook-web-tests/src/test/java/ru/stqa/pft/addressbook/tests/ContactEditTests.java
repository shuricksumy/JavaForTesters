package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

import java.util.Comparator;
import java.util.List;

public class ContactEditTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().createSimple();
    }
  }

  @Test
  public void testContactEdit() throws Exception {
    List<ContactData> contactsBeforeTest = app.contact().list();
    int index = contactsBeforeTest.size() - 1;
    int idEditElement = contactsBeforeTest.get(index).getId();
    ContactData contact1 = new ContactData()
        .withFirstName("firstUserNameEdit")
        .withMiddleName("middleUserNameEdit")
        .withLastName("lastUserNameEdit")
        .withNickname("nickUserNameEdit")
        .withTitle("userTitleEdit")
        .withCompanyName("testCompanyEdit")
        .withAddress("London, Edit str, 33")
        .withMobilePhone("+987654321")
        .withEmailAddress("email@edit.com")
        .withHomeSite("https://edit.com")
        .withBirthday(new DateData()
            .withDay("13")
            .withMonth("April")
            .withYear("2002"))
        .withId(idEditElement);

    app.contact().edit(index, contact1);

    List<ContactData> contactsAfterTest = app.contact().list();
    Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size());

    contactsBeforeTest.removeIf(c -> c.getId() == idEditElement);
    contactsBeforeTest.add(contact1);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    contactsBeforeTest.sort(byId);
    contactsAfterTest.sort(byId);
    Assert.assertEquals(contactsBeforeTest, contactsAfterTest);
  }
}
