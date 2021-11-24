package ru.course.mantisbt.tests;

import org.testng.annotations.Test;
import ru.course.mantisbt.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTest extends TestBase {

  @Test
  public void loginTest() throws IOException {
    HttpSession session = app.newSesion();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
