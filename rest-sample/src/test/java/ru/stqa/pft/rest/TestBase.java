package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {


  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    Issue issue = getIssue(issueId);
    if (issue == null) return false;
    if (issue.getStateName().equals("Open")) return true;
    return false;
  }

  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json?page=1&limit=10000"))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public Issue getIssue(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issue = parsed.getAsJsonObject().get("issues");
    Set<Issue> issues = new Gson().fromJson(issue, new TypeToken<Set<Issue>>() {
    }.getType());
    if (issues.size() == 0) return null;
    return issues.iterator().next();
  }


  public int createIssue(Issue issue) throws IOException {
    String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
        .bodyForm(new BasicNameValuePair("subject", issue.getSubject()),
            new BasicNameValuePair("description", issue.getDescription())))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }


  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }
}
