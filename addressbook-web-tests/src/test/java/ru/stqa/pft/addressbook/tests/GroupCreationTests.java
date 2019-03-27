package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    GroupData group1 = new GroupData("TestGroup1", "TestGroup1Header", "TestGroup1Footer");

    app.getNavigationHelper().goToGroupPage();
    List<GroupData> groupsBeforeTest = app.getGroupHelper().getGroupList();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(group1);
    app.getGroupHelper().submitGroupForm();
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> groupsAfterTest = app.getGroupHelper().getGroupList();
    Assert.assertEquals(groupsAfterTest.size(), groupsBeforeTest.size() + 1);

    int max = groupsAfterTest.stream().max(((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))).get().getId();
    group1.setId(max);
    groupsBeforeTest.add(group1);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    groupsBeforeTest.sort(byId);
    groupsAfterTest.sort(byId);
    Assert.assertEquals(groupsBeforeTest, groupsAfterTest);

  }

}
