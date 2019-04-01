package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().createSimple();
    }
  }

  @Test
  public void testContactDelete() throws Exception {
    Contacts contactsBeforeTest = app.contact().all();
    ContactData deleteContact = contactsBeforeTest.iterator().next();
    app.contact().delete(deleteContact);
    Contacts contactsAfterTest = app.contact().all();
    Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size() - 1);

    assertThat(contactsAfterTest, equalTo(contactsBeforeTest.without(deleteContact)));
  }
}
