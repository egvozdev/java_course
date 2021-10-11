package ru.course.tests;

import org.testng.annotations.Test;
import ru.course.model.GroupData;

public class GroupCreateTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));

  }

}
