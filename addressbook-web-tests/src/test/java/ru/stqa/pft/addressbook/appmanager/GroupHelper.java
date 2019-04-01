package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
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

  public void deleteFirstGroup() {
    selectFirstGroup();
    click(By.name("delete"));
  }

  public void selectFirstGroup() {
    click(By.name("selected[]"));
  }

  public void initGroupEdit() {
    click(By.name("edit"));
  }

  public void submitChanges() {
    click(By.name("update"));
  }

  public boolean isAnyExist() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create() {
    initGroupCreation();
    fillGroupForm(new GroupData().withName("TestGroup1"));
    submitGroupForm();
  }

  public int getContactCount() {
    return getCounterElementsBy(By.name("selected[]"));
  }

  public List<GroupData> list() {
    wd.manage().timeouts().implicitlyWait(gs.getQuickWaiterTime(), TimeUnit.SECONDS);
    List<GroupData> groups = new ArrayList<GroupData>();
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

  public void selectGroupByNumber(int number) {
    wd.findElements(By.name("selected[]")).get(number).click();
  }

  public void delete(int i) {
    selectGroupByNumber(i);
    click(By.name("delete"));
    returnToGroupsList();
  }

  public void modify(GroupData groupEdit, int index) {
    selectGroupByNumber(index);
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
