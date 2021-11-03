package ru.course.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.course.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerators {
  @Parameter(names = "-c", description = "Contacts Count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main (String args[]) throws IOException {
    ContactDataGenerators generator = new ContactDataGenerators();
    JCommander jCommander  = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();



  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format "+ format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    XStream xstream = new XStream();
//    xstream.alias("group", ContactData.class);
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s\n", contact.getName(), contact.getSurname()));
    }
    writer.close();
  }

  private  List<ContactData> generateContacts(int count) {

    List<ContactData> contacts = new ArrayList<>();

    for (int i = 0; i <count; i++) {
      contacts.add(new ContactData().withName(String.format("Evgeny %s", i))
                      .withSurname(String.format("Gvozdev %s", i))
                      .withAdress(String.format("Sarov %s", i))
                      .withMobile(String.format("+7555%s", i))
                      .withEmail(String.format("Evgeny@Gvozdevs@gmail.com", i))
                      .withCompany(String.format("Rosbank s", i))

              );
    }
    return contacts;
  }
}
