package ru.course.tests;

import org.testng.annotations.*;

public class DeleteGroupTest extends TestBase {

  @Test
  public void testDeleteGroup() throws Exception {
    app.gotoGroupPage();
    app.selectGroups();
    app.deleteSelectedGroups();
    app.returnToGroupPage();
  }


}
