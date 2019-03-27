package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTests extends TestBase {

    @Test
    public void testContactDelete() throws Exception {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isAnyContactsExist()) {
            app.getContactHelper().createSimpleContact();
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> contactsBeforeTest = app.getContactHelper().getContactList();
        app.getContactHelper().selectContactByNumber(contactsBeforeTest.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> contactsAfterTest = app.getContactHelper().getContactList();
        Assert.assertEquals(contactsAfterTest.size(), contactsBeforeTest.size() - 1);

        contactsBeforeTest.remove(contactsBeforeTest.size() - 1);
        Assert.assertEquals(contactsAfterTest, contactsBeforeTest);
    }
}
