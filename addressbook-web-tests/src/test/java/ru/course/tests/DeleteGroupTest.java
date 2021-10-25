package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.course.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class DeleteGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1-autocreated").withFooter("test2").withHeader("test3"));
      app.goTo().groupPage();
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    int indexToRemove = before.size()-1;
    app.group().selectGroups(indexToRemove);
    app.group().deleteSelectedGroups();
    before.remove(indexToRemove);
    app.goTo().groupPage();
    List<GroupData> after = app.group().list();
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
