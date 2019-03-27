package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.DateData;

import java.util.Comparator;
import java.util.List;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdit() throws Exception {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isAnyContactsExist()) {
            app.getContactHelper().createSimpleContact();
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> contactsBeforeTest = app.getContactHelper().getContactList();
        int idEditElement = contactsBeforeTest.get(contactsBeforeTest.size() - 1).getId();
        ContactData contact1 = new ContactData("firstUserNameEdit", "middleUserNameEdit", "lastUserNameEdit",
                "nickUserNameEdit", "userTitleEdit", "testCompanyEdit", "London, Edit str, 33",
                "+987654321", "email@edit.com", "https://edit.com",
                new DateData("13", "April", "2002"), idEditElement);
        app.getContactHelper().selectContactByNumber(contactsBeforeTest.size() - 1);
        app.getContactHelper().initContactEdit(contactsBeforeTest.size() - 1);
        app.getContactHelper().fillContactForm(contact1, false);
        app.getContactHelper().submitChanges();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> contactsAfterTest = app.getContactHelper().getContactList();
        Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size());

        contactsBeforeTest.removeIf(c -> c.getId() == idEditElement);
        contactsBeforeTest.add(contact1);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        contactsBeforeTest.sort(byId);
        contactsAfterTest.sort(byId);
        Assert.assertEquals(contactsBeforeTest, contactsAfterTest);
    }
}
