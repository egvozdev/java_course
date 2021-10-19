package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1-autocreated", "test2", "test3"));
      app.getNavigationHelper().gotoGroupPage();
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int indexToChange = before.size()-1;
    app.getGroupHelper().selectGroups(indexToChange);
    app.getGroupHelper().initGroupModification();
    GroupData modifiedGroup = new GroupData("test-edited", "test22", "test33");
    app.getGroupHelper().fillGroupForm(modifiedGroup);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
//    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals (before.size(), after.size());

    modifiedGroup.setId(before.get(indexToChange).getId());
    before.remove(indexToChange);
    before.add(modifiedGroup);

    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
