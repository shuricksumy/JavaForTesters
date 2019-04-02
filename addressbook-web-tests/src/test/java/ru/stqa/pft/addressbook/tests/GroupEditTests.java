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
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().createSimple();
      app.goTo().groupPage();
    }
  }

  @Test
  public void testGroupEdit() throws Exception {
    Groups groupsBeforeTest = app.group().all();
    GroupData modifiedGroup = groupsBeforeTest.iterator().next();

    GroupData group = new GroupData()
        .withName("TestGroup1Edit")
        .withHeader("TestGroup1HeaderEdit")
        .withFooter("TestGroup1FooterEdit")
        .withId(modifiedGroup.getId());

    app.group().modify(group);
    Groups groupsAfterTest = app.group().all();
    assertEquals(groupsAfterTest.size(), groupsBeforeTest.size());

    groupsBeforeTest.remove(modifiedGroup);
    groupsBeforeTest.add(group);
    assertEquals(groupsBeforeTest, groupsAfterTest);
    assertThat(groupsAfterTest, equalTo(groupsBeforeTest.without(modifiedGroup).withAdded(group)));
  }
}
