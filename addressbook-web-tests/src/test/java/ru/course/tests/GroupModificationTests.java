package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.GroupData;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class GroupModificationTests extends TestBase {

  @BeforeMethod(alwaysRun = true)
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1-autocreated").withHeader("test2").withFooter("test3"));
      app.goTo().groupPage();
    }
  }
  
  @Test
  public void testGroupModification() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test-edited").withFooter("test22").withHeader("test33");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals (before.size(), after.size());

//    modifiedGroup.withId(before.get(indexToChange).getId());
    before.remove(modifiedGroup);
    before.add(group);

//    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//    before.sort(byId);
//    after.sort(byId);
    Assert.assertEquals(before, after);

  }



}
