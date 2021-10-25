package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class DeleteContactTests extends TestBase{

  @Test(enabled = false)
  public void testDeleteContact() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Evgeny", "+79601830803", "Gvozdev", "egvozdev@gmail.com", "PAO Rosbank", null));
      app.getNavigationHelper().gotoHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int indexToRemove = before.size() - 1;
    app.getContactHelper().selectContact(indexToRemove);
    app.getContactHelper().deleteContact();;
    app.getNavigationHelper().confirm();;
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(indexToRemove);
//    before.remove(1);
    System.out.println("index  " + indexToRemove);
    System.out.println("before " + before.size());
    System.out.println("after " + after.size());
    Assert.assertEquals (before.size(), after.size());
//    for (int i = 0; i < before.size(); i++) {
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());

    before.sort(byId);
    after.sort(byId);
//    System.out.println("before1 " + before.size());
//    System.out.println("after1 " + after.size());
    Assert.assertEquals(before, after);
  }

}
