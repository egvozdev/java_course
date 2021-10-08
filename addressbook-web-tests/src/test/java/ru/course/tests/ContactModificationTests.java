package ru.course.tests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    app.getContactHelper().editContact();
    app.getContactHelper().changeContactForm(By.name("address"), "Sarov");
    app.getContactHelper().submitContactModification();
  }

}
