package ru.course.tests;

import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.GroupData;

public class DeleteContactTests extends TestBase{

  @Test
  public void testDeleteContact() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Evgeny", "+79601830803", "Gvozdev", "egvozdev@gmail.com", "PAO Rosbank", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();;
    app.getNavigationHelper().confirm();;

  }

}
