package ru.course.model;

import java.util.Objects;

public class ContactData {
  private String name;
  private String mobile;
  private String surname;
  private String email;
  private String company;
  private String group;
  private Integer id;

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(Integer id) {
    this.id = id;
    return this;
  }


  public Integer getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass())  {
//      System.out.println("ret obj\n");
      return false; }
    ContactData that = (ContactData) o;
//    System.out.println("name " + Objects.equals(name, that.name) + "bef " + name + "aft " + that.name + "id " + id);
    return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname, id);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", mobile='" + mobile + '\'' +
            ", surname='" + surname + '\'' +
            ", email='" + email + '\'' +
            ", company='" + company + '\'' +
            ", group='" + group + '\'' +
            ", id='" + id + '\'' +
            '}';
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
