package ru.course.mantisbt.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
  private final ApplicationManager app;
  private final WebDriver wd;

  public RegistrationHelper (ApplicationManager app) {
      this.app = app;
      this.wd = app.getDriver();
  }

  public void start(String user, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
