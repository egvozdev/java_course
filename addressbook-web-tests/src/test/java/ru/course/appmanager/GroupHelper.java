package ru.course.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.course.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("groups"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.xpath("//div[@id='content']/form/input[5]"));
  }

  public void selectGroups(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectGroupsById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData groupData) {
    initGroupCreation();
    fillGroupForm(groupData);
    submitGroupCreation();
  }

  public void modify(int indexToChange, GroupData modifiedGroup) {
    selectGroups(indexToChange);
    initGroupModification();
    fillGroupForm(modifiedGroup);
    submitGroupModification();
    returnToGroupPage();
  }
  public void modify(GroupData group) {
    selectGroupsById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public void delete(int indexToRemove) {
    selectGroups(indexToRemove);
    deleteSelectedGroups();
    returnToGroupPage();
  }
  public void delete(GroupData group) {
    selectGroupsById(group.getId());
    deleteSelectedGroups();
    returnToGroupPage();
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements  = wd.findElements(By.cssSelector("span.group"));
    for (WebElement el: elements) {
      String name = el.getText();
      int id = Integer.parseInt((el.findElement(By.tagName("input")).getAttribute("value")));
//      System.out.println("id " + id);
      groups.add(new GroupData().withName(name).withId(id));
    }
    return groups;
  }
  public Set<GroupData> all() {
    Set<GroupData> groups = new HashSet<>();
    List<WebElement> elements  = wd.findElements(By.cssSelector("span.group"));
    for (WebElement el: elements) {
      String name = el.getText();
      int id = Integer.parseInt((el.findElement(By.tagName("input")).getAttribute("value")));
//      System.out.println("id " + id);
      groups.add(new GroupData().withName(name).withId(id));
    }
    return groups;
  }
}
