package com.shurick.trn.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class DistanceTests {
  DecimalFormat df = new DecimalFormat(".##");

  @Test
  public void testDistanceFromStart() {
    Point start = new Point(0, 0);

    Point a = new Point(5, 5);
    Assert.assertEquals(df.format(start.getDistanceTo(a)), df.format(7.07));

    Point b = new Point(0, 0);
    Assert.assertEquals(df.format(start.getDistanceTo(b)), df.format(0));

    Point c = new Point(-5, -5);
    Assert.assertEquals(df.format(start.getDistanceTo(c)), df.format(7.07));

    Point d = new Point(0, 5);
    Assert.assertEquals(df.format(start.getDistanceTo(d)), df.format(5));

    Point e = new Point(0, -5);
    Assert.assertEquals(df.format(start.getDistanceTo(e)), df.format(5));

    Point f = new Point(5, 0);
    Assert.assertEquals(df.format(start.getDistanceTo(f)), df.format(5));

    Point g = new Point(-5, 0);
    Assert.assertEquals(df.format(start.getDistanceTo(g)), df.format(5));
  }

  @Test
  public void testDistanceBetweenPoints() {
    Point a1 = new Point(0, 0);
    Point a2 = new Point(5, 5);
    Assert.assertEquals(df.format(a1.getDistanceTo(a2)), df.format(7.07));
    Assert.assertEquals(df.format(a2.getDistanceTo(a1)), df.format(7.07));
    Assert.assertEquals(a2.getDistanceTo(a1), a1.getDistanceTo(a2));

    Point b1 = new Point(-27.39, 34.43);
    Point b2 = new Point(76.91, -23.43);
    Assert.assertEquals(df.format(b1.getDistanceTo(b2)), df.format(119.27));
    Assert.assertEquals(df.format(b2.getDistanceTo(b1)), df.format(119.27));
    Assert.assertEquals(b2.getDistanceTo(b1), b1.getDistanceTo(b2));

    Point c1 = new Point(123.4587, 15.0);
    Point c2 = new Point(1879.54, -6357.147);
    Assert.assertEquals(df.format(c1.getDistanceTo(c2)), df.format(6609.7));
    Assert.assertEquals(df.format(c2.getDistanceTo(c1)), df.format(6609.7));
    Assert.assertEquals(c2.getDistanceTo(c1), c1.getDistanceTo(c2));

  }

}
