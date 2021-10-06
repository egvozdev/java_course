package ru.course;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactTest {
  private WebDriver wd;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreateContact() throws Exception {
    wd.get("https://localhost/addressbook/group.php");
    sendRegularField("user", "admin");
    sendRegularField("pass", "secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
    wd.findElement(By.linkText("add new")).click();
    sendName("Evgeny");
    sendRegularField("mobile", "+79601830803");
    sendRegularField("lastname", "Gvozdev");
    sendRegularField("email", "egvozdev@gmail.com");
    sendRegularField("company", "PAO Rosbank");
    sendRegularField("address", "Nizhniy Novgorod");
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void sendRegularField(String field, String value) {
    wd.findElement(By.name(field)).click();
    wd.findElement(By.name(field)).clear();
    wd.findElement(By.name(field)).sendKeys(value);
  }

  private void sendName(String name) {
    sendRegularField("firstname", name);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  
}
