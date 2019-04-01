package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    ContactData contact1 = new ContactData()
        .withFirstName("firstUserName")
        .withMiddleName("middleUserName")
        .withLastName("lastUserName")
        .withNickname("nickUserName")
        .withTitle("userTitle")
        .withCompanyName("testCompany")
        .withAddress("London, Main str, 33")
        .withMobilePhone("+123456789")
        .withEmailAddress("email@test.com")
        .withHomeSite("https://test.com")
        .withBirthday(new DateData()
            .withDay("1")
            .withMonth("January")
            .withYear("2000")
        );

    List<ContactData> contactsBeforeTest = app.contact().list();
    app.contact().create(contact1);
    List<ContactData> contactsAfterTest = app.contact().list();
    Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size() + 1);

    int max = contactsAfterTest.stream().max(((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))).get().getId();
    contact1.withId(max);
    contactsBeforeTest.add(contact1);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    contactsBeforeTest.sort(byId);
    contactsAfterTest.sort(byId);
    Assert.assertEquals(contactsBeforeTest, contactsAfterTest);
  }

}
