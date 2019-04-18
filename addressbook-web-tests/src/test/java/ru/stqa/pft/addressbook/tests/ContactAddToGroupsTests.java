package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddToGroupsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().createSimple();
    }

    if (app.db().contacts()
        .stream()
        .filter(c -> c.getGroups().size() == 0)
        .count() == 0) {
      app.goTo().homePage();
      app.contact().createSimple();
    }
  }

  @Test
  public void testContactAddToGroup() throws Exception {
    GroupData group = app.db().groups().iterator().next();
    Contacts contactsBeforeTest = app.db().contacts();

    ContactData modifiedContact = contactsBeforeTest.stream().filter(c -> c.getGroups().size() == 0).findAny().get();

    app.goTo().homePage();
    app.contact().addToGroup(modifiedContact.withGroups(group));
    app.goTo().homePage();

    Contacts contactsAfterTest = app.db().contacts();
    assertEquals(contactsBeforeTest, contactsAfterTest);

    assertThat(contactsAfterTest.stream().filter(c -> c.equals(modifiedContact)).findAny().get().getGroups(),
        equalTo(modifiedContact.withGroups(group).getGroups()));

    verifyContactListInUI();
  }


}
