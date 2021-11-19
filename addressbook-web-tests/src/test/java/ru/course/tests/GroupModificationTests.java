package ru.course.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.GroupData;
import ru.course.model.Groups;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupModificationTests extends TestBase {

  @BeforeMethod(alwaysRun = true)
  public void ensurePreconditions() {
    /* precondition for db interface */
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1-autocreated").withHeader("test2").withFooter("test3"));
      app.goTo().groupPage();
    };

    /* precondition for web interface
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1-autocreated").withHeader("test2").withFooter("test3"));
      app.goTo().groupPage();
    }
    */
  }
  
  @Test(enabled = true)
  public void testGroupModification() {
    Groups before = app.db().groups();

    app.goTo().groupPage();
    // web
    // Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test-edited").withFooter("test22").withHeader("test33");
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    // Groups after = app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }


}
