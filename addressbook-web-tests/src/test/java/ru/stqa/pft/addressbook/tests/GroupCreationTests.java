package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    GroupData group1 = new GroupData("TestGroup1", "TestGroup1Header", "TestGroup1Footer");

    app.getNavigationHelper().goToGroupPage();
    List <GroupData> groupsBeforeTest =  app.getGroupHelper().getGroupList();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(group1);
    app.getGroupHelper().submitGroupForm();
    app.getNavigationHelper().goToGroupPage();
    List <GroupData> groupsAfterTest =  app.getGroupHelper().getGroupList();
    Assert.assertEquals(groupsAfterTest.size(),groupsBeforeTest.size() + 1);

    int max = 0;
    for (GroupData g : groupsAfterTest){
      if (g.getId() > max){
        max = g.getId();
      }
    }
    group1.setId(max);
    groupsBeforeTest.add(group1);
    Assert.assertEquals(new HashSet<>(groupsBeforeTest), new HashSet<>(groupsAfterTest));

  }

}
