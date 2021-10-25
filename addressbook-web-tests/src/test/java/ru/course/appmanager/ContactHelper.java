package ru.course.appmanager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.course.model.ContactData;
import ru.course.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

  public void editContact(int i) {
//    click(By.xpath("//img[@alt='Edit']"));
    String sel = "a[href*=\"edit.php?id=" + i + "\"]";
    wd.findElements(By.cssSelector(sel)).get(0).click();

  }

  public void submitContactModification() {
    click (By.xpath("//div[@id='content']/form/input[22]"));

  }

  public void changeContactForm(By locator,  String value ) {
    type(locator, value);
  }

   public void delete() {
    click(By.xpath("//input[@value='Delete']"));
   }

  public void select(Integer index) {
//    click(By.xpath("//tr[3]/td/input"));}
//    click(By.xpath("//td/input"));
      wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void create(ContactData contact) {
    //String sss = wd.findElement(By.name("search_count")).getAttribute("value");
    creatNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//td/input"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
//    List<WebElement> elements  = wd.findElements(By.cssSelector("tr"));
//    List<WebElement> elements  = wd.findElements(By.cssSelector("title~entry"));

    List<WebElement> elements  = wd.findElements(By.name("entry"));

    List<WebElement> el1;
    for (WebElement el: elements) {
//      System.out.println("text" + el.getText());
//      System.out.println("atr" + el.getAttribute());
//      System.out.println("tag" + el.getTagName());
//      System.out.println("child 1 " + el.findElement(By.cssSelector(":first-child")).getText());
//      System.out.println("child 2 " + el.findElement(By.cssSelector(":nth-child(2)")).getText());
//      System.out.println("child 3 " + el.findElement(By.cssSelector(":nth-child(3)")).getText());
//      System.out.println("id  " + el.findElement(By.tagName("input")).getAttribute("value"));
//      System.out.println("id  " + el.findElement(By.name("input")).getAttribute());
      String name = el.findElement(By.cssSelector(":nth-child(3)")).getText();
      String surname = el.findElement(By.cssSelector(":nth-child(2)")).getText();
      Integer id = Integer.valueOf(el.findElement(By.tagName("input")).getAttribute("value"));
//      System.out.println("id " + id);
      ContactData contact = new ContactData().withName(name).withSurname(surname).withId(id);
      contacts.add(contact);
    }
    return contacts;
  }
}
