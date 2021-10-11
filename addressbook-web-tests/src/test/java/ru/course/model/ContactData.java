package ru.course.model;

public class ContactData {
  private final String name;
  private final String mobile;
  private final String surname;
  private final String email;
  private final String company;
  private String group;

  public ContactData(String name, String mobile, String surname, String email, String company, String group) {
    this.name = name;
    this.mobile = mobile;
    this.surname = surname;
    this.email = email;
    this.company = company;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getMobile() {
    return mobile;
  }

  public String getSurname() {
    return surname;
  }

  public String getEmail() {
    return email;
  }

  public String getCompany() {
    return company;
  }

  public String getGroup() { return group; }
}
