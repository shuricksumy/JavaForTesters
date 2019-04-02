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

  public void createSimple() {
    initGroupCreation();
    fillGroupForm(new GroupData().withName("TestGroup1"));
    submitGroupForm();
    groupCache = null;
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }

    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
    groupCache = new Groups();
    List<WebElement> groupsElement = wd.findElements(By.cssSelector("span.group"));
    for (WebElement el : groupsElement) {
      String groupName = el.getText();
      int id = Integer.parseInt(el.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withName(groupName).withId(id);
      groupCache.add(group);
    }
    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
    return new Groups(groupCache);
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
  }

  public void delete(GroupData deletedGroup) {
    selectGroupById(deletedGroup.getId());
    click(By.name("delete"));
    groupCache = null;
    returnToGroupsList();
  }

  public void modify(GroupData groupEdit) {
    selectGroupById(groupEdit.getId());
    initGroupEdit();
    fillGroupForm(groupEdit);
    submitChanges();
    groupCache = null;
    returnToGroupsList();
  }

  public void create(GroupData group1) {
    initGroupCreation();
    fillGroupForm(group1);
    submitGroupForm();
    groupCache = null;
    returnToGroupsList();
  }

  public int count() {
    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
    int count = wd.findElements(By.name("selected[]")).size();
    wd.manage().timeouts().implicitlyWait(gs.getDefaultWaiterTime(), TimeUnit.SECONDS);
    return count;
  }
}
