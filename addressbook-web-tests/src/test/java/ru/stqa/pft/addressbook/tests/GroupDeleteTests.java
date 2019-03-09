package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTests extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().deleteFirstGroup();
    app.getGroupHelper().returnToGroupsList();
  }

}
