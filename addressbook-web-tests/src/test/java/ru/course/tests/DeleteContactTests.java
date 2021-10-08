package ru.course.tests;

import org.testng.annotations.Test;
import ru.course.model.ContactData;

public class DeleteContactTests extends TestBase{

  @Test
  public void testDeleteContact() throws Exception {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();;
    app.getNavigationHelper().confirm();;

  }

}
