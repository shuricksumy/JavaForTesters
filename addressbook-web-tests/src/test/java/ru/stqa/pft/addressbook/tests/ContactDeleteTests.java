package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().createSimple();
    }
  }

  @Test
  public void testContactDelete() throws Exception {
    List<ContactData> contactsBeforeTest = app.contact().list();
    int index = contactsBeforeTest.size() - 1;

    app.contact().delete(index);

    List<ContactData> contactsAfterTest = app.contact().list();
    Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size() - 1);

    contactsBeforeTest.remove(contactsBeforeTest.size() - 1);
    Assert.assertEquals(contactsAfterTest, contactsBeforeTest);
  }
}
