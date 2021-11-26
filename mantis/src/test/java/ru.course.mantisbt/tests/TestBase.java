package ru.course.mantisbt.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.course.mantisbt.appmanager.ApplicationManager;

import javax.swing.text.StyledEditorKit;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(
          System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
//    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
//    app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
  }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        if (!issue.getStatus().getName().equals("resolved")) {
            return true;
        } else return false;
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException, ServiceException, javax.xml.rpc.ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
