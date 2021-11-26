package ru.course.mantisbt.tests;

import org.testng.annotations.Test;
import ru.course.mantisbt.model.Issue;
import ru.course.mantisbt.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

  @Test
  public void testGetProjects() throws MalformedURLException, RemoteException, ServiceException {
    skipIfNotFixed(0000002);

    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {

    long now = System.currentTimeMillis();
    String issueName = String.format("Test Issue %s", now);

    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary(issueName)
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

}
