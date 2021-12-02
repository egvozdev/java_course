package ru.course.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.Contacts;
import ru.course.model.GroupData;
import ru.course.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase {

  Groups groupsHaveContacts = new Groups();

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1-autocreated").withHeader("test2").withFooter("test3"));
    }

    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withName("Evgeny").withMobile("+79601830803").withSurname("Gvozdev").withEmail("egvozdev@gmail.com").withCompany("PAO Rosbank"));
      app.goTo().HomePage();
    }

    Groups beforeGroupList = app.db().groups();
    for (GroupData group : beforeGroupList) {
      if (group.getContacts().size() != 0) {
        groupsHaveContacts.add(group);
      }
    }
    if (groupsHaveContacts.size() == 0) {
      app.goTo().HomePage();

      Contacts contactList = app.db().contacts();
      Groups groupList = app.db().groups();

      GroupData group = groupList.iterator().next();
      ContactData contact = contactList.iterator().next();

      app.contact().addToGroup(contact, group);

      Contacts contactListNew = app.db().contacts();
      Groups groupListNew = app.db().groups();

      ContactData contactNew = app.contact().refresh(contact, contactListNew);
      GroupData groupNew  = app.group().refresh(group, groupListNew);

      groupsHaveContacts.add(groupNew);

    }

  }

  @Test
  public void testContactToGroupRemoval() {

    GroupData groupWithContact = groupsHaveContacts.iterator().next();
    ContactData contactWithGroup = groupWithContact.getContacts().iterator().next();

    app.goTo().HomePage();
    app.contact().filterContactsByGroup(groupWithContact.getId());
    app.contact().removeContactFromFilteredGroups(contactWithGroup, groupWithContact);
    app.contact().returnToHomePage();

    Contacts contactList = app.db().contacts();
    ContactData contactWithoutGroup = app.contact().refresh(contactWithGroup, contactList);
    assertThat(contactWithoutGroup.getGroups(), equalTo(contactWithGroup.getGroups().without(groupWithContact)));

  }
}
