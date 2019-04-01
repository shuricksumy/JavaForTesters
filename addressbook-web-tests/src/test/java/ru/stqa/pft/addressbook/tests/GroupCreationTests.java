package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    GroupData group1 = new GroupData()
        .withName("TestGroup1")
        .withHeader("TestGroup1Header")
        .withFooter("TestGroup1Footer");

    app.goTo().groupPage();
    List<GroupData> groupsBeforeTest = app.group().list();
    app.group().create(group1);
    List<GroupData> groupsAfterTest = app.group().list();
    Assert.assertEquals(groupsAfterTest.size(), groupsBeforeTest.size() + 1);

    int max = groupsAfterTest.stream().max(((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))).get().getId();
    group1.withId(max);
    groupsBeforeTest.add(group1);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    groupsBeforeTest.sort(byId);
    groupsAfterTest.sort(byId);
    Assert.assertEquals(groupsBeforeTest, groupsAfterTest);

  }

}
