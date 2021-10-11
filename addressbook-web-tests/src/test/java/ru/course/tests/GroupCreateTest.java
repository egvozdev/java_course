package ru.course.tests;

import org.testng.annotations.Test;
import ru.course.model.GroupData;

public class GroupCreateTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1-2", "test2", "test3"));
    }

  }

}
