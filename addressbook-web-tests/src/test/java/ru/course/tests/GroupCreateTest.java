package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreateTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
//    Comparator<? super GroupData> byIdStream = (o1, o2) -> Integer.compare(o1.getId(),o2.getId());
//    int max = before.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
/*
    Comparator<? super GroupData> byIdStream = new Comparator<GroupData>() {
      @Override
      public int compare(GroupData o1, GroupData o2) {
        return Integer.compare(o1.getId(),o2.getId());
      }
    };
    int max = before.stream().max(byIdStream).get().getId();
 */
    //    int before = app.getGroupHelper().getGroupCount();
    GroupData newgroup = new GroupData("test1", "test2", "test3");
    app.getGroupHelper().createGroup(newgroup);
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size() + 1, after.size());

    newgroup.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(newgroup);

    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
