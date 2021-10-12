package ru.course.tests;

import org.testng.annotations.*;
import ru.course.model.GroupData;

public class DeleteGroupTest extends TestBase {

  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1-autocreated", "test2", "test3"));
      app.getNavigationHelper().gotoGroupPage();
    }
    app.getGroupHelper().selectGroups();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
