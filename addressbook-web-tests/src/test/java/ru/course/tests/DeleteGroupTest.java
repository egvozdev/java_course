package ru.course.tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.course.model.GroupData;
import ru.course.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteGroupTest extends TestBase {

  @BeforeMethod(alwaysRun = true)
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1-autocreated").withFooter("test2").withHeader("test3"));
      app.goTo().groupPage();
    }
  }

  @Test(enabled = false)
  public void testDeleteGroup() throws Exception {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
//    app.goTo().groupPage();
    Groups after = app.group().all();
    assertThat(before.size()-1, equalTo(after.size()));
    assertThat(after, equalTo(
            before.without(deletedGroup)));

  }



}
