package ru.course.tests;

import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhone extends TestBase{

  @Test(enabled = true)
  public void testContactPhones() throws Exception {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEdit = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHome(), equalTo(cleaned(contactInfoFromEdit.getHome())));
    assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEdit.getMobile())));
    assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEdit.getWork())));
/*
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertThat(before.size(), equalTo(after.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
 */
  }
  public String cleaned(String phone) {
      return phone.replaceAll("[-\\s()]", "");
  }

}
