package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    GroupData group = new GroupData()
        .withName("TestGroup1")
        .withHeader("TestGroup1Header")
        .withFooter("TestGroup1Footer");

    app.goTo().groupPage();
    Groups groupsBeforeTest = app.group().all();
    app.group().create(group);
    Groups groupsAfterTest = app.group().all();
    assertThat(groupsAfterTest.size(), equalTo(groupsBeforeTest.size() + 1));

    assertThat(groupsAfterTest, equalTo(groupsBeforeTest
        .withAdded(group.withId(groupsAfterTest.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
