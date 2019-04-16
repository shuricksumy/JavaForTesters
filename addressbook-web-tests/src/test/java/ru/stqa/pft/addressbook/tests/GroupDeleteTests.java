package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.group().createSimple();
      app.goTo().groupPage();
    }
  }

  @Test
  public void testGroupDelete() throws Exception {
    Groups groupsBeforeTest = app.db().groups();
    GroupData deletedGroup = groupsBeforeTest.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);

    assertThat(app.group().count(), equalTo(groupsBeforeTest.size() - 1));

    Groups groupsAfterTest = app.db().groups();
    assertThat(groupsAfterTest, equalTo(groupsBeforeTest.without(deletedGroup)));
  }

}
