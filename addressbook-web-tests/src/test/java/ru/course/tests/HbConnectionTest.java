package ru.course.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.course.model.ContactData;
import ru.course.model.GroupData;

import java.util.List;

public class HbConnectionTest {

  private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      try {
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
      }
      catch (Exception e) {
        // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
        // so destroy it manually.
        e.printStackTrace();
        StandardServiceRegistryBuilder.destroy( registry );
      }
    }

  @Test
  public void hbConnectionTest(){
    Session session = sessionFactory.openSession();

    session.beginTransaction();

    List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
    for ( ContactData contact:  result ) {
      System.out.println( "Contact  " + contact);
    }

    List<GroupData> resultGroup = session.createQuery( "from GroupData" ).list();
    for ( GroupData group :  resultGroup ) {
      System.out.println( "Group  " + group);
    }
    session.getTransaction().commit();
    session.close();
  }
}
