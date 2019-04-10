package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.DateData;

import java.io.File;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    File photo = new File("src/test/resources/logo.png");

    ContactData contact = new ContactData()
        .withFirstName("firstUserName")
        .withMiddleName("middleUserName")
        .withLastName("lastUserName")
        .withNickname("nickUserName")
        .withTitle("userTitle")
        .withCompanyName("testCompany")
        .withAddress("London, Main str, 33")
        .withMobilePhone("+123456789")
        .withEmailAddressFirst("email@test.com")
        .withHomeSite("https://test.com")
        .withBirthday(new DateData()
            .withDay("1")
            .withMonth("January")
            .withYear("2000")
        ).withPhoto(photo);

    Contacts contactsBeforeTest = app.contact().all();
    app.contact().create(contact);

    Assert.assertEquals(app.contact().count(), contactsBeforeTest.size() + 1);

    Contacts contactsAfterTest = app.contact().all();
    MatcherAssert.assertThat(contactsAfterTest, CoreMatchers.equalTo(contactsBeforeTest
        .withAdded(contact.withId(contactsAfterTest.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
