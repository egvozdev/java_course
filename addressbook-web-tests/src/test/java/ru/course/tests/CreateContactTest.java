package ru.course.tests;

import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.Contacts;
import ru.course.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader( new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line  = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType()); //List<ContactData>.class
    return contacts.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testCreateContact(ContactData newContact) throws Exception {
//    File photo = new File("src/test/java/ru/course/resource/evg-Visa.jpg");
    app.goTo().HomePage();
    Contacts before = app.db().contacts();
//    Contacts before = app.contact().all();
//    ContactData newContact = new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank").withPhoto(photo);
    app.contact().create(newContact);
    Contacts after = app.db().contacts();
//    Contacts after = app.contact().all();
    assertThat(after.size()-1, equalTo(before.size()));
    assertThat(after, equalTo(
            before.withAdded(newContact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }


}
