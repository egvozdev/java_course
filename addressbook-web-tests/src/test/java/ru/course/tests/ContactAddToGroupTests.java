package ru.course.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.Contacts;
import ru.course.model.GroupData;
import ru.course.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

  Contacts contactsWithoutGroups = new Contacts();

  @BeforeMethod
  public void ensurePreconditions() {

    Contacts beforeContactList = app.db().contacts();
    for (ContactData contact : beforeContactList) {
      if (contact.getGroups().size() == 0) {
        contactsWithoutGroups.add(contact);
      }
    }
    if (contactsWithoutGroups.size() == 0) {
      app.contact().create(new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank"));
      Contacts afterContactList = app.db().contacts();
      for (ContactData contact : afterContactList) {
        if (contact.getGroups().size() == 0) {
          contactsWithoutGroups.add(contact);
        }
      }
    }
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1-autocreated").withHeader("test2").withFooter("test3"));
    }

  }


  @Test
  public void TestContactToGroupAddition() {
    app.goTo().HomePage();
    ContactData before = contactsWithoutGroups.iterator().next();
    Groups groupList = app.db().groups();
    GroupData group = groupList.iterator().next();
    app.contact().addToGroup(before, group);

    Contacts contactList = app.db().contacts();
    ContactData after = app.contact().refresh(before, contactList);
    assertThat(after.getGroups(), equalTo(before.getGroups().withAdded(group)));

  }
}
