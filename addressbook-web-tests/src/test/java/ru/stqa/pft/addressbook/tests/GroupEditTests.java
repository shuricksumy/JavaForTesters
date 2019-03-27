package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupEditTests extends TestBase {

  @Test
  public void testGroupEdit() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isAnyGroupsExist()) {
      app.getGroupHelper().createSimpleGroup();
      app.getNavigationHelper().goToGroupPage();
    }
    List<GroupData> groupsBeforeTest =  app.getGroupHelper().getGroupList();
    GroupData groupEdit = new GroupData("TestGroup1Edit", "TestGroup1HeaderEdit",
        "TestGroup1FooterEdit", groupsBeforeTest.get(groupsBeforeTest.size() - 1).getId());
    app.getGroupHelper().selectGroupByNumber(groupsBeforeTest.size() - 1);
    app.getGroupHelper().initGroupEdit();
    app.getGroupHelper().fillGroupForm(groupEdit);
    app.getGroupHelper().submitChanges();
    app.getGroupHelper().returnToGroupsList();
    List <GroupData> groupsAfterTest =  app.getGroupHelper().getGroupList();
    Assert.assertEquals(groupsAfterTest.size(),groupsBeforeTest.size());

    groupsBeforeTest.remove(groupsBeforeTest.size() - 1);
    groupsBeforeTest.add(groupEdit);
    Assert.assertEquals(new HashSet<>(groupsBeforeTest), new HashSet<>(groupsAfterTest));
  }

}
