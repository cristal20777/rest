package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.Executor;

import static org.testng.AssertJUnit.assertEquals;

public class RestTests {
  @Test
  public void restCreateIssue() {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue();
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  private int createIssue(Issue newIssue) {
    return 0;
  }

  private Set<Issue> getIssues() {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?pageSize=50000"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return null;
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("b31e382ca8445202e66b03aaf31508a3", "");
  }


}
