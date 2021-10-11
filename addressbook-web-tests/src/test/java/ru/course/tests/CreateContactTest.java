package ru.course.tests;

import org.testng.annotations.Test;
import ru.course.model.ContactData;

public class CreateContactTest extends TestBase {

  @Test
  public void testCreateContact() throws Exception {
    app.getContactHelper().createContact(new ContactData("Evgeny", "+79601830803", "Gvozdev", "egvozdev@gmail.com", "PAO Rosbank", "test1"));
  }


}
