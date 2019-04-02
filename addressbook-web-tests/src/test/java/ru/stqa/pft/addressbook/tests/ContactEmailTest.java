package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      ContactData contact = new ContactData()
          .withFirstName("firstUserName")
          .withLastName("lastUserName")
          .withAddress("London, Main str, 33")
          .withEmailAddressFirst("email1@test.com")
          .withEmailAddressSecond("email2@test.com")
          .withEmailAddressThird("email3@test.com")
          .withMobilePhone("333-333-33");
      app.contact().create(contact);
    }
  }

  @Test
  public void testContactEmail() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getEmailAddressAll(), equalTo(mergeEmails(contactInfoForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmailAddressFirst(), contact.getEmailAddressSecond(), contact.getEmailAddressThird())
        .stream().filter((s) -> !s.equals(""))
        .collect(Collectors.joining("\n"));
  }

}
