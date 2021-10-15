package ru.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {


  public static void main(String[] args) {
    List<String> lanquages = new ArrayList<String>();
    List<String> lanquages1 = Arrays.asList("C++", "Python");

    lanquages.add("Java");
    lanquages.add("C");

    for (String l: lanquages) {
      System.out.println("Lang: " + l);
    }
    for (int i = 0; i < lanquages1.size(); i++) {
      System.out.println("Lang1: " + lanquages1.get(i));
    }

  }
}
