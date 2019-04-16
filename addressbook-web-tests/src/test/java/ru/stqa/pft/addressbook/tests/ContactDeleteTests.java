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

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().createSimple();
    }
  }

  @Test
  public void testContactDelete() throws Exception {
    Contacts contactsBeforeTest = app.db().contacts();
    ContactData deleteContact = contactsBeforeTest.iterator().next();

    app.goTo().homePage();
    app.contact().delete(deleteContact);

    Assert.assertEquals(app.contact().count(), contactsBeforeTest.size() + -1);

    Contacts contactsAfterTest = app.db().contacts();
    assertThat(contactsAfterTest, equalTo(contactsBeforeTest.without(deleteContact)));

    verifyContactListInUI();
  }
}
