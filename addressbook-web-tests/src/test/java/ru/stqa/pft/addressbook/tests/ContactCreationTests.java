package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.DateData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    ContactData contact = new ContactData()
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

    Contacts contactsBeforeTest = app.contact().all();
    app.contact().create(contact);
    Contacts contactsAfterTest = app.contact().all();
    Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size() + 1);


    MatcherAssert.assertThat(contactsAfterTest, CoreMatchers.equalTo(contactsBeforeTest
        .withAdded(contact.withId(contactsAfterTest.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
