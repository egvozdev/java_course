package ru.course.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.course.model.GroupData;
import ru.course.model.Groups;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreateTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsCsv() throws IOException {
    List<Object[]> groups = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader( new File("src/test/resources/group_data.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      groups.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line  = reader.readLine();
    }

    return groups.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader( new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
//      String[] split = line.split(";");
//      groups.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      xml += line;
      line  = reader.readLine();
    }
    XStream  xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>)xstream.fromXML(xml);
    return groups.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader( new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
//      String[] split = line.split(";");
//      groups.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      json += line;
      line  = reader.readLine();
    }
    Gson  gson = new Gson();
//    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {}.getType()); //List<GroupData>.class
    return groups.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData newgroup) throws Exception {

    app.goTo().groupPage();
    Groups before = app.group().all();
//    GroupData newgroup = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    app.group().create(newgroup);
    app.goTo().groupPage();
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(newgroup.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
  @Test(enabled = true)
  public void testBadGroupCreation() throws Exception {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData newgroup = new GroupData().withName("test1'");
    app.group().create(newgroup);
    app.goTo().groupPage();
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

}
