package ru.course.mantisbt.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper (ApplicationManager app) {
      super(app);
  }

  public void start(String user, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), user);
    type(By.name("email"),email);
    click(By.cssSelector("input[value='Зарегистрироваться']"));
  }

  public void finish(String link, String password) {
    wd.get(link);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));

  }
}
