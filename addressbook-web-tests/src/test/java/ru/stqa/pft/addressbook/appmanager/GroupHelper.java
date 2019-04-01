package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void submitGroupForm() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    typeInputField(By.name("group_name"), groupData.getName());
    typeInputField(By.name("group_header"), groupData.getHeader());
    typeInputField(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void returnToGroupsList() {
    click(By.linkText("group page"));
  }

  public void initGroupEdit() {
    click(By.name("edit"));
  }

  public void submitChanges() {
    click(By.name("update"));
  }

  public void create() {
    initGroupCreation();
    fillGroupForm(new GroupData().withName("TestGroup1"));
    submitGroupForm();
  }

  public Groups all() {
    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
    Groups groups = new Groups();
    List<WebElement> groupsElement = wd.findElements(By.cssSelector("span.group"));
    for (WebElement el : groupsElement) {
      String groupName = el.getText();
      int id = Integer.parseInt(el.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withName(groupName).withId(id);
      groups.add(group);
    }
    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
    return groups;
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void delete(GroupData deletedGroup) {
    selectGroupById(deletedGroup.getId());
    click(By.name("delete"));
    returnToGroupsList();
  }

  public void modify(GroupData groupEdit) {
    selectGroupById(groupEdit.getId());
    initGroupEdit();
    fillGroupForm(groupEdit);
    submitChanges();
    returnToGroupsList();
  }

  public void create(GroupData group1) {
    initGroupCreation();
    fillGroupForm(group1);
    submitGroupForm();
    returnToGroupsList();
  }


}
