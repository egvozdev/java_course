package ru.course.mantisbt.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.mantisbt.model.MailMessage;
import ru.course.mantisbt.model.UserData;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ChangePasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void passwordResetTest() throws MessagingException, IOException {
    app.registration().login(new UserData().withLogin("administrator").withPassword("root"));
    UserData user = app.db().users()
            .stream().filter((u) -> (!u.getLogin().equals("administrator")))
            .collect(Collectors.toList()).iterator().next();
    app.registration().pressResetByAdmin(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    String newPassword = "new-password";
    app.registration().finish(confirmationLink, newPassword);
    app.newSesion().login(user.getLogin(), newPassword);
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}


