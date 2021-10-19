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

  public ContactData(String name, String mobile, String surname, String email, String company, String group) {
    this.name = name;
    this.mobile = mobile;
    this.surname = surname;
    this.email = email;
    this.company = company;
    this.group = group;
  }

  public ContactData(String name, String surname, Integer id) {
    this.name = name;
    this.surname = surname;
    this.id = id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass())  {
      System.out.println("ret obj\n"); return false; }
    ContactData that = (ContactData) o;
    System.out.println("name " + Objects.equals(name, that.name) + "bef " + name + "aft " + that.name + "id " + id);
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
