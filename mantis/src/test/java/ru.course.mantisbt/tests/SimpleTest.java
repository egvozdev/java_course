package ru.course.mantisbtyyyyyyyyyyy.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.mantisbt.tests.TestBase;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleTest extends TestBase {


  @Test(enabled = true)
  public void simpleTest() throws Exception {
    System.out.println("start");
  }



}
