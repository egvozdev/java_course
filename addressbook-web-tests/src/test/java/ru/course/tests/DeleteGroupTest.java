package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.course.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class DeleteGroupTest extends TestBase {

  @BeforeMethod(alwaysRun = true)
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1-autocreated").withFooter("test2").withHeader("test3"));
      app.goTo().groupPage();
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {

    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
//    app.goTo().groupPage();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals (before.size()-1, after.size());

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);

  }



}
