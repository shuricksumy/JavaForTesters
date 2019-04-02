package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().createSimple();
      app.goTo().groupPage();
    }
  }

  @Test
  public void testGroupDelete() throws Exception {
    Groups groupsBeforeTest = app.group().all();
    GroupData deletedGroup = groupsBeforeTest.iterator().next();
    app.group().delete(deletedGroup);
    Groups groupsAfterTest = app.group().all();
    assertEquals(groupsAfterTest.size(), groupsBeforeTest.size() - 1);

    assertThat(groupsAfterTest, equalTo(groupsBeforeTest.without(deletedGroup)));
  }

}
