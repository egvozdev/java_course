package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreateTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
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
    GroupData newgroup = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    app.group().create(newgroup);
    app.goTo().groupPage();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(before.size() + 1, after.size());

//    newgroup.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    newgroup.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(newgroup);

//    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//    before.sort(byId);
//    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
