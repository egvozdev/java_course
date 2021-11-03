package ru.course.appmanager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.course.model.ContactData;
import ru.course.model.Contacts;
import ru.course.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
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
    attach(By.name("photo"), contactData.getPhoto());
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

   public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
   }

  public void select(Integer index) {
//    click(By.xpath("//tr[3]/td/input"));}
//    click(By.xpath("//td/input"));
      wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    //String sss = wd.findElement(By.name("search_count")).getAttribute("value");
    creatNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void delete(int indexToRemove) {
    select(indexToRemove);
    deleteContact();
  }
  public void delete(ContactData contact) {
    selectById(contact.getId());
    deleteContact();
    confirm();
    returnToHomePage();
  }

  public void modify(List<ContactData> before, int indexToChange, ContactData contact) {
    editContact(before.get(indexToChange).getId());
    fillContactForm(contact, false);
//    app.getContactHelper().changeContactForm(By.name("address"), "Sarov");
    submitContactModification();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    editContact(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
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

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements  = wd.findElements(By.name("entry"));

    List<WebElement> el1;
    for (WebElement el: elements) {
      String name = el.findElement(By.cssSelector(":nth-child(3)")).getText();
      String surname = el.findElement(By.cssSelector(":nth-child(2)")).getText();
      String allPhones = el.findElement(By.cssSelector(":nth-child(6)")).getText();
      String allEmails = el.findElement(By.cssSelector(":nth-child(5)")).getText();
      String allAdresses = el.findElement(By.cssSelector(":nth-child(4)")).getText();
//      String[] phones = allPhones.split("\n");
      Integer id = Integer.valueOf(el.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withName(name).withSurname(surname).withId(id)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAllAdresses(allAdresses);
      contacts.add(contact);
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModoficationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String address2 = wd.findElement(By.name("address2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobile(mobile).withWorkPhone(work)
            .withAdress(address).withAdress2(address2)
            .withEmail(email).withEmail2(email2).withEmail3(email3);

  }

  private void initContactModoficationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

//    wd.findElement(By.xpath(String.format("//inut[@value='%s']/../../td[8]/a", id))).click();
//    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//    wd.findElement(By.cssSelector(String.format("s[href='edit.php?id=%s]", id))).click();
  }
}
