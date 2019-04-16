package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{new GroupData().withName("GrName1").withHeader("Header1").withFooter("Footer1")});
    list.add(new Object[]{new GroupData().withName("GrName2").withHeader("Header2").withFooter("Footer2")});
    list.add(new Object[]{new GroupData().withName("GrName3").withHeader("Header3").withFooter("Footer3")});
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      List<Object[]> list = new ArrayList<Object[]>();
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      List<Object[]> list = new ArrayList<Object[]>();
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType()); //List<GroupData>.class
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromJSON")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups groupsBeforeTest = app.db().groups();
    app.group().create(group);

    assertThat(app.group().count(), equalTo(groupsBeforeTest.size() + 1));

    Groups groupsAfterTest = app.db().groups();
    assertThat(groupsAfterTest, equalTo(groupsBeforeTest
        .withAdded(group.withId(groupsAfterTest.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() throws Exception {

    GroupData group = new GroupData()
        .withName("TestGroup1'")
        .withHeader("TestGroup1Header")
        .withFooter("TestGroup1Footer");

    app.goTo().groupPage();
    Groups groupsBeforeTest = app.db().groups();
    app.group().create(group);

    assertThat(app.group().count(), equalTo(groupsBeforeTest.size()));

    Groups groupsAfterTest = app.db().groups();
    assertThat(groupsAfterTest, equalTo(groupsBeforeTest));

  }

}
