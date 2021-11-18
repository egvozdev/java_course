package ru.course.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddress extends TestBase{

  @BeforeMethod
  private void ensurePrerequizites() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank"));
      app.goTo().HomePage();
    }

  }

  @Test(enabled = true)
  public void testContactAdresses() throws Exception {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEdit = app.contact().infoFromEditForm(contact);
    String merged = mergeAdresses(contactInfoFromEdit);
    String merged1 = cleaned(contact.getAllAdresses());

    assertThat(contact.getAllAdresses(), equalTo(mergeAdresses(contactInfoFromEdit)));

  }

  private String mergeAdresses(ContactData contact) {
    return contact.getAdress().replaceAll("[ \\t]+", " ").replaceAll(" \n", "\n");
/*
    return Arrays.asList(contact.getAdress())
            .stream()
            .filter((s)-> !s.equals(""))
            .map(ContactAddress::cleaned)
            .collect(Collectors.joining(" "));
  */
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s+", " ").replaceAll("\\n", " ");
  }
}

