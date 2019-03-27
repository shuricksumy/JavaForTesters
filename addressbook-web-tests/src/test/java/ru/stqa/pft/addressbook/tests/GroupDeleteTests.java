package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTests extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isAnyGroupsExist()) {
      app.getGroupHelper().createSimpleGroup();
      app.getNavigationHelper().goToGroupPage();
    }
    List<GroupData> groupsBeforeTest =  app.getGroupHelper().getGroupList();
    app.getGroupHelper().deleteGroupByNumber(groupsBeforeTest.size() - 1);
    app.getGroupHelper().returnToGroupsList();
    List <GroupData> groupsAfterTest =  app.getGroupHelper().getGroupList();
    Assert.assertEquals(groupsAfterTest.size(),groupsBeforeTest.size() - 1);

    groupsBeforeTest.remove(groupsBeforeTest.size() - 1);
    Assert.assertEquals(groupsAfterTest, groupsBeforeTest);
  }

}
