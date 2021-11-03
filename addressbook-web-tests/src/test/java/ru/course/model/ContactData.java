package ru.course.model;

import java.io.File;
import java.util.Objects;

public class ContactData {
  private String name;
  private String mobile;
  private String surname;
  private String email;
  private String email3;
  private String email2;
  private String adress;
  private String adress2;
  private String company;
  private String group;
  private String home;
  private String work;
  private String allPhones;
  private String allEmails;
  private String allAdresses;
  private int id;
  private File photo;



  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;

  }

  public String getAdress() {
    return adress;
  }

  public ContactData withAdress(String adress) {
    this.adress = adress;
    return this;

  }public String getAdress2() {
    return adress2;
  }

  public ContactData withAdress2(String adress2) {
    this.adress2 = adress2;
    return this;

  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;

  }

  public String getAllAdresses() {
    return allAdresses;
  }

  public ContactData withAllAdresses(String allAdresses) {
    this.allAdresses = allAdresses;
    return this;

  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getHome() {
    return home;
  }

  public ContactData withHomePhone(String home) {
    this.home = home;
    return this;
  }

    public String getWork() {
    return work;
  }

  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }


  public ContactData withName(String name) {
    this.name = name;
    return this;
  }


  public ContactData withSurname(String surname) {
    this.surname = surname;
    return this;
  }


  public ContactData withLastName(String surname) {
    this.surname = surname;
    return this;
  }

  public ContactData withFirstName(String name) {
    this.name = name;
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


  public int getId() {
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

  public File getPhoto() { return photo; }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }
}
