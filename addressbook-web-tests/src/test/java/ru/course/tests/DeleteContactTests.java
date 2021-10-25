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

public class DeleteContactTests extends TestBase{

  @BeforeMethod
  private void ensurePrerequizites() {
    if (! app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank"));
      app.goTo().HomePage();
    }
  }

  @Test(enabled = true)
  public void testDeleteContact() throws Exception {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
//    app.goTo().confirm();
//    app.goTo().HomePage();
    Contacts after = app.contact().all();
    assertThat(after.size()+1, equalTo(before.size()));
    assertThat(after, equalTo(before.without(deletedContact)));
  }



}
