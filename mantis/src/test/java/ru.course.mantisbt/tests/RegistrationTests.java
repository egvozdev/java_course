package ru.course.mantisbt.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

  @Test
  public void registrationTest () {
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
