package ru.course.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

  @Test(enabled = true)
  public void testCreateContact() throws Exception {
    File photo = new File("src/test/java/ru/course/resource/evg-Visa.jpg");
    System.out.println("exist " + photo.exists());
    System.out.println("path " + photo.getAbsolutePath());
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    ContactData newContact = new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank").withPhoto(photo);
    app.contact().create(newContact);
    Contacts after = app.contact().all();
    assertThat(after.size()-1, equalTo(before.size()));
    assertThat(after, equalTo(
            before.withAdded(newContact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }


}
