package ru.course.tests;

import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhone extends TestBase{

  @Test(enabled = true)
  public void testContactPhones() throws Exception {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEdit = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEdit)));

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
            .stream()
            .filter((s)-> !s.equals(""))
            .map(ContactPhone::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
