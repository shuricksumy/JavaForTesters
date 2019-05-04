package ru.stqa.pft.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SkipTestBaseOnBugStatus extends TestBase {

  @Test
  public void bugIsOpenAndTestIsSkipped() throws IOException {
    skipIfNotFixed(1244);
    Assert.assertEquals("Test run successfully", "Test Ignored");
  }

}
