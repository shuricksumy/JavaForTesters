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

        ContactData contact1 = new ContactData("firstUserName", "middleUserName", "lastUserName",
                "nickUserName", "userTitle", "testCompany", "London, Main str, 33",
                "+123456789", "email@test.com", "https://test.com",
                new DateData("1", "January", "2000"));

        List<ContactData> contactsBeforeTest = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact1, true);
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> contactsAfterTest = app.getContactHelper().getContactList();
        Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size() + 1);

        int max = contactsAfterTest.stream().max(((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))).get().getId();
        contact1.setId(max);
        contactsBeforeTest.add(contact1);

        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        contactsBeforeTest.sort(byId);
        contactsAfterTest.sort(byId);
        Assert.assertEquals(contactsBeforeTest, contactsAfterTest);
    }

}
