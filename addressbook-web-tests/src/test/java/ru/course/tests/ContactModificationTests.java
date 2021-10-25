package ru.course.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.Contacts;
import ru.course.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  private void ensurePrerequizites() {
    if (! app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank"));
      app.goTo().HomePage();
    }
  }

  @Test(enabled = true)
  public void testContactModification() throws Exception {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Evgeny1").withMobile("+79601830803").withSurname("Gvozdev1").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertThat(before.size(), equalTo(after.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }




}
