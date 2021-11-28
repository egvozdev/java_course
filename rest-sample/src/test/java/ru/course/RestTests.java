package ru.course;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

        @Test
        public void testCreateIssue() throws IOException {
        skipIfNotFixed(1639);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Evg issue").withDescription("New evg issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

}
