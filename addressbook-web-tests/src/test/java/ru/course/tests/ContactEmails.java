package ru.course.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmails extends TestBase{

  @BeforeMethod
  private void ensurePrerequizites() {
    if (! app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank"));
      app.goTo().HomePage();
    }
  }

  @Test(enabled = true)
  public void testContactEmails() throws Exception {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEdit = app.contact().infoFromEditForm(contact);

    String merged = mergeEmails(contactInfoFromEdit);
    String merged1 = cleaned(mergeEmails(contactInfoFromEdit));
    String merged2 = cleaned(contact.getAllEmails());

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEdit)));

  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream()
            .filter((s)-> s!=null)
            .filter((s)-> !s.equals(""))
            .map(ContactEmails::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s+", " ");
  }

}
