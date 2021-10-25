package ru.course.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.GroupData;
import ru.course.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreateTest extends TestBase {


  @Test(enabled = true)
  public void testGroupCreation() throws Exception {
    GroupData gr = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    System.out.println(gr.toString());
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData newgroup = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    app.group().create(newgroup);
    app.goTo().groupPage();
    Groups after = app.group().all();
    assertThat(before.size() + 1, equalTo(after.size()));
    assertThat(after, equalTo(
            before.withAdded(newgroup.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

}
