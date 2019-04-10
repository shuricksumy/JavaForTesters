package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String filePath;

  @Parameter(names = "-t", description = "File format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
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
    if (format.equals("xml")) {
      saveXML(contacts, new File(filePath));
    } else if (format.equals("json")) {
      saveJSON(contacts, new File(filePath));
    } else {
      System.out.println("Wrong file format is selected - " + format);
    }
  }

  private void saveJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
          .withFirstName(String.format("firstUserName_%s", i))
          .withMiddleName(String.format("middleUserName_%s", i))
          .withLastName(String.format("lastUserName_%s", i))
          .withNickname(String.format("nickUserName_%s", i))
          .withTitle(String.format("userTitle_%s", i))
          .withCompanyName(String.format("testCompany_%s", i))
          .withAddress(String.format("London, Main str, %s", i))
          .withMobilePhone(String.format("+123456789_%s", i))
          .withEmailAddressFirst(String.format("email+%s@test.com", i))
          .withHomeSite(String.format("https://test_%s.com", i)));
    }
    return contacts;
  }
}