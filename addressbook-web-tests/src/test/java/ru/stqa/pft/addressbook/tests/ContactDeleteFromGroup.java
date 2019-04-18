package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteFromGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().createSimple();
    }

    if (app.db().contacts()
        .stream()
        .filter(c -> c.getGroups().size() == 1)
        .count() == 0) {

      Groups groups = app.db().groups();

      ContactData simpleContact = new ContactData()
          .withFirstName("firstSimpleName")
          .withLastName("lastSimpleName")
          .withAddress("London, Simple str, 1")
          .withMobilePhone("+123456789")
          .withEmailAddressFirst("simple@test.com")
          .withGroups(groups.iterator().next())
          .withPhoto(new File("src/test/resources/logo.png"));
      app.goTo().homePage();
      app.contact().create(simpleContact);
    }
  }

  @Test
  public void testContactRemoveFromGroup() throws Exception {
    Contacts contactsBeforeTest = app.db().contacts();

    ContactData modifiedContact = contactsBeforeTest.stream().filter(c -> c.getGroups().size() == 1).findAny().get();
    GroupData group = modifiedContact.getGroups().iterator().next();


    app.goTo().homePage();
    app.contact().removeFromGroup(modifiedContact);
    app.goTo().homePage();

    Contacts contactsAfterTest = app.db().contacts();
    assertEquals(contactsBeforeTest, contactsAfterTest);

    assertThat(contactsAfterTest.stream().filter(c -> c.equals(modifiedContact)).findAny().get().getGroups(),
        equalTo(modifiedContact.withoutGroups(group).getGroups()));

    verifyContactListInUI();
  }

}
