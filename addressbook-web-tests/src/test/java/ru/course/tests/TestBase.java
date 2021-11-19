package ru.course.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.course.appmanager.ApplicationManager;
import ru.course.model.GroupData;
import ru.course.model.Groups;

import javax.swing.text.StyledEditorKit;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(GroupCreateTest.class);

  protected static final ApplicationManager app = new ApplicationManager(
          System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod(alwaysRun = true)
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with params ", Arrays.asList(p));
  }
  @BeforeMethod(alwaysRun = true)
  public void logTestEnd(Method m) {
    logger.info("Finish test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups  = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g)->new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

}
