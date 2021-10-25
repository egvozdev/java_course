package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = true)
  public void testContactModification() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank"));
      app.goTo().gotoHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int indexToChange = before.size() - 1;
    app.getContactHelper().editContact(before.get(indexToChange).getId());
    ContactData contact = new ContactData().withName("Evgeny1").withMobile("+79601830803").withSurname("Gvozdev1").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank");
    app.getContactHelper().fillContactForm(contact, false);
//    app.getContactHelper().changeContactForm(By.name("address"), "Sarov");
    app.getContactHelper().submitContactModification();
    app.goTo().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    contact.withId(before.get(indexToChange).getId());
    before.remove(indexToChange);
    before.add(contact);
    Assert.assertEquals (before.size(), after.size());

    Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
//    for (int i = 0; i < before.size(); i++) {
//      System.out.println("bef sorted i " + i + before.get(i).toString());
//      System.out.println("aft sorted " + after.get(i).toString());
//    }
    Assert.assertEquals(before, after);
  }

}
