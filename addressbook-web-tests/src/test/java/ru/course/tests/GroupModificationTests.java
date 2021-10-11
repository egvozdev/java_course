package ru.course.tests;

import org.testng.annotations.Test;
import ru.course.model.GroupData;


public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1-autocreated", "test2", "test3"));
    }
    app.getGroupHelper().selectGroups();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test-edited", "test22", "test33"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();}

}
