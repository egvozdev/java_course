package ru.course.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    click(By.linkText("groups"));
  }

  public void HomePage() {
    click(By.linkText("home"));
  }

  public void confirm() {
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.switchTo().alert().accept();
  }
}
