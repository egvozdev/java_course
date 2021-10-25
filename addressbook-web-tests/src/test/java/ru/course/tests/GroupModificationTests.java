package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1-autocreated").withHeader("test2").withFooter("test3"));
      app.goTo().groupPage();
    }
  }
  
  @Test
  public void testGroupModification() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    int indexToChange = before.size()-1;
    GroupData modifiedGroup = new GroupData().withName("test-edited").withFooter("test22").withHeader("test33");
    app.group().modify(indexToChange, modifiedGroup);
    List<GroupData> after = app.group().list();
    Assert.assertEquals (before.size(), after.size());

    modifiedGroup.withId(before.get(indexToChange).getId());
    before.remove(indexToChange);
    before.add(modifiedGroup);

    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }



}
