package ru.course.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.model.ContactData;

import java.util.Comparator;
import java.util.List;

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
    List<ContactData> before = app.contact().getContactList();
    int indexToRemove = before.size() - 1;
    app.contact().select(indexToRemove);
    app.contact().delete();;
    app.goTo().confirm();;
    app.goTo().HomePage();

    List<ContactData> after = app.contact().getContactList();
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
