package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      ContactData contact = new ContactData()
          .withFirstName("firstUserName")
          .withLastName("lastUserName")
          .withAddress("London, Main str, 33")
          .withEmailAddressFirst("email1@test.com")
          .withMobilePhone("333-333-33");
      app.contact().create(contact);
    }
  }

  @Test
  public void testContactEmail() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoForm = app.contact().infoFromEditForm(contact);
    assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoForm.getAddress())));
  }

  public static String cleaned(String address) {
    return address.replaceAll("\n", "").replaceAll(",\\s", ",");
  }

}
