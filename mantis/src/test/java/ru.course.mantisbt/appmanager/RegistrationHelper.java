package ru.course.mantisbt.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.course.mantisbt.model.UserData;

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
  public void pressResetByAdmin(UserData user) {
    goToUsersLists();
    goToEditUserPageById(user.getId());
    resetPassword();
  }

  public void login(UserData user) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), user.getLogin());
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), user.getPassword());
    click(By.cssSelector("input[type='submit']"));
  }

  public void goToUsersLists() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void goToEditUserPageById(int id) {
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id)));
  }

  public void resetPassword() {
//    click(cssSelector("input[value='Reset Password']"));
//    click(By.cssSelector("#manage-user-reset-form > fieldset > span > input"));
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }

}
