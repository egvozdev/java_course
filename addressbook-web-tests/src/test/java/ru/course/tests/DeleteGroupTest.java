package ru.course.tests;

import org.hamcrest.CoreMatchers;
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
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1-autocreated").withHeader("test2").withFooter("test3"));
      app.goTo().groupPage();
    };
    app.goTo().groupPage();
  }

  @Test(enabled = true)
  public void testDeleteGroup() throws Exception {

    app.goTo().groupPage();
    Groups before = app.db().groups();
//    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
//    app.goTo().groupPage();
    assertThat(app.group().count(), equalTo(before.size()-1));
    Groups after = app.db().groups();
//    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.without(deletedGroup)));

  }



}
