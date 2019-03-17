package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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

  public boolean isAnyGroupsExist(){
    return isElementPresent(By.name("selected[]"));
  }
  public void createSimpleGroup(){
    initGroupCreation();
    fillGroupForm(new GroupData("TestGroup1", null, null));
    submitGroupForm();
  }
}
