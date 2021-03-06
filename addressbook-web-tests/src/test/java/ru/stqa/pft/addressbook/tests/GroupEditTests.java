package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupEditTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.group().createSimple();
      app.goTo().groupPage();
    }
  }

  @Test
  public void testGroupEdit() throws Exception {
    Groups groupsBeforeTest = app.db().groups();
    GroupData modifiedGroup = groupsBeforeTest.iterator().next();

    GroupData group = new GroupData()
        .withName("TestGroup1Edit")
        .withHeader("TestGroup1HeaderEdit")
        .withFooter("TestGroup1FooterEdit")
        .withId(modifiedGroup.getId());

    app.goTo().groupPage();
    app.group().modify(group);

    assertThat(app.group().count(), equalTo(groupsBeforeTest.size()));

    Groups groupsAfterTest = app.db().groups();

    groupsBeforeTest.remove(modifiedGroup);
    groupsBeforeTest.add(group);
    assertEquals(groupsBeforeTest, groupsAfterTest);
    assertThat(groupsAfterTest, equalTo(groupsBeforeTest.without(modifiedGroup).withAdded(group)));

    verifyGroupListInUI();
  }
}
