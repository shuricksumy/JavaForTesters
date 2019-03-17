package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupEditTests extends TestBase {

  @Test
  public void testGroupEdit() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isAnyGroupsExist()) {
      app.getGroupHelper().createSimpleGroup();
      app.getNavigationHelper().goToGroupPage();
    }
    app.getGroupHelper().selectFirstGroup();
    app.getGroupHelper().initGroupEdit();
    app.getGroupHelper().fillGroupForm(new GroupData("TestGroup1Edit", "TestGroup1HeaderEdit", "TestGroup1FooterEdit"));
    app.getGroupHelper().submitChanges();
    app.getGroupHelper().returnToGroupsList();
  }

}
