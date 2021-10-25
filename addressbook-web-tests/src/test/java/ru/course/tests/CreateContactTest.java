package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class CreateContactTest extends TestBase {

  @Test(enabled = false)
  public void testCreateContact() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData newContact = new ContactData("Evgeny", "+79601830803", "Gvozdev", "egvozdev@gmail.com", "PAO Rosbank", null);
    app.getContactHelper().createContact(newContact);
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals (before.size(), after.size()-1);

//    for (int i = 0; i < before.size(); i++) {
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    newContact.setId(after.stream().max(byId).get().getId());
    before.add(newContact);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
