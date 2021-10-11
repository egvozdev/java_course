package ru.course.appmanager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.course.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void creatNewContact() {
    click (By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getName());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("lastname"), contactData.getSurname());
    type(By.name("email"), contactData.getEmail());
    type(By.name("company"), contactData.getCompany());
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click (By.xpath("//div[@id='content']/form/input[22]"));

  }

  public void changeContactForm(By locator,  String value ) {
    type(locator, value);
  }
   public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
   }

  public void selectContact() {
    click(By.xpath("//tr[3]/td/input"));}
}
