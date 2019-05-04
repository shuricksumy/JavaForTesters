package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupFakeTests extends TestBase{
  @Test
  public void testBadGroupCreation() throws Exception {

    GroupData group = new GroupData()
        .withName("TestGroupFake'")
        .withHeader("TestGroup1Header")
        .withFooter("TestGroup1Footer");

    app.goTo().groupPage();
    Groups groupsBeforeTest = app.db().groups();
    app.group().create(group);

    assertThat(app.group().count(), equalTo(groupsBeforeTest.size() + 1));

    Groups groupsAfterTest = app.db().groups();
    assertThat(groupsAfterTest, equalTo(groupsBeforeTest));

    verifyGroupListInUI();
  }
}
