package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.course.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeleteGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1-autocreated", "test2", "test3"));
      app.getNavigationHelper().gotoGroupPage();
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int indexToRemove = before.size()-1;
    app.getGroupHelper().selectGroups(indexToRemove);
    app.getGroupHelper().deleteSelectedGroups();
    before.remove(indexToRemove);
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals (before.size(), after.size());

//    for (int i = 0; i < before.size(); i++) {
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
