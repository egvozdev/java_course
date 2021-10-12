package ru.course.tests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import ru.course.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Evgeny", "+79601830803", "Gvozdev", "egvozdev@gmail.com", "PAO Rosbank", null));
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().editContact();
    app.getContactHelper().changeContactForm(By.name("address"), "Sarov");
    app.getContactHelper().submitContactModification();
  }

}
