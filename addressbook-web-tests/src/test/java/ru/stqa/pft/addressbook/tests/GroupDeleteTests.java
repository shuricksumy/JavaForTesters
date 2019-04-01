package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create();
      app.goTo().groupPage();
    }
  }

  @Test
  public void testGroupDelete() throws Exception {
    List<GroupData> groupsBeforeTest = app.group().list();
    int index = groupsBeforeTest.size() - 1;
    app.group().delete(index);
    List<GroupData> groupsAfterTest = app.group().list();
    Assert.assertEquals(groupsAfterTest.size(), groupsBeforeTest.size() - 1);
    groupsBeforeTest.remove(index);
    Assert.assertEquals(groupsAfterTest, groupsBeforeTest);
  }

}
