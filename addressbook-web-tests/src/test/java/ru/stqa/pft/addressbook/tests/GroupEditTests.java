package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupEditTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create();
      app.goTo().groupPage();
    }
  }

  @Test
  public void testGroupEdit() throws Exception {
    List<GroupData> groupsBeforeTest = app.group().list();
    int index = groupsBeforeTest.size() - 1;

    GroupData groupEdit = new GroupData()
        .withName("TestGroup1Edit")
        .withHeader("TestGroup1HeaderEdit")
        .withFooter("TestGroup1FooterEdit")
        .withId(groupsBeforeTest.get(index).getId());

    app.group().modify(groupEdit, index);
    List<GroupData> groupsAfterTest = app.group().list();
    Assert.assertEquals(groupsAfterTest.size(), groupsBeforeTest.size());

    groupsBeforeTest.remove(index);
    groupsBeforeTest.add(groupEdit);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    groupsBeforeTest.sort(byId);
    groupsAfterTest.sort(byId);
    Assert.assertEquals(groupsBeforeTest, groupsAfterTest);
  }

}
