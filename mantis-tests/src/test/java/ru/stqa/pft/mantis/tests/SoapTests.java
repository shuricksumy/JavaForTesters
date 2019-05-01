package ru.stqa.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

  @Test(enabled = false)
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000001); //Issue 1 is closed
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue()
        .withSummary("Test issue")
        .withDescription("Test issue description")
        .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testIgnoreDueToOpenedIssue() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000002); //Issue 2 is opened
    Assert.assertEquals("Test run successfully", "Test Ignored");
  }
}
