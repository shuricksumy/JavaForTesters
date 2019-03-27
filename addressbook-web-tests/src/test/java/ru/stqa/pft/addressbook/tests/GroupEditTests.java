package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    groupsBeforeTest.sort(byId);
    groupsAfterTest.sort(byId);
    Assert.assertEquals(groupsBeforeTest, groupsAfterTest);
  }

}
