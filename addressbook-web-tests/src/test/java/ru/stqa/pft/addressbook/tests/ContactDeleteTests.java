package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase{

  @Test
  public void testContactDelete() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isAnyContactsExist()){
      app.getContactHelper().createSimpleContact();
      app.getNavigationHelper().goToHomePage();
    }
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().goToHomePage();
  }
}
