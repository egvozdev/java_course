package ru.course.appmanager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

  public void fillContactForm(ContactData contactData, boolean creating) {
    type(By.name("firstname"),contactData.getName());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("lastname"), contactData.getSurname());
    type(By.name("email"), contactData.getEmail());
    type(By.name("company"), contactData.getCompany());
    if (creating) {
      if ( contactData.getGroup() != null ) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    //    if (isElementPresent(By.name("new_group"))) {
//      if ( contactData.getGroup() != null ) {
//        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//      }
//    }
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
//    click(By.xpath("//tr[3]/td/input"));}
    click(By.xpath("//td/input"));}

  public void createContact(ContactData contact) {
    //String sss = wd.findElement(By.name("search_count")).getAttribute("value");
    creatNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//td/input"));
  }
}
