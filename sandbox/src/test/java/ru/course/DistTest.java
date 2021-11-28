package ru.course;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistTest {
  @Test
  public void testDist1() {
    Point p1  = new Point(1,1);
    Point p2  = new Point(1,2);
    Point p3  = new Point(2,2);
    Point p4  = new Point(-1,-1);

    Assert.assertEquals(p1.distance(p2), 1);
        Assert.assertEquals(p1.distance(p1), 0.0);

  }
  @Test
  public void testDist2() {
    Point p1  = new Point(1,1);
    Point p2  = new Point(1,2);
    Point p3  = new Point(2,2);
    Point p4  = new Point(-1,-1);

    Assert.assertEquals(p1.distance(p3), Math.sqrt(2));
    Assert.assertEquals(p1.distance(p4), Math.sqrt(8));
    Assert.assertEquals(p4.distance(p1), Math.sqrt(8));
  }
}
