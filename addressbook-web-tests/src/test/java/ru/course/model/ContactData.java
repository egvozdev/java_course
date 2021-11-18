package ru.course.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jdk.internal.instrumentation.TypeMapping;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name="addressbook")
public class ContactData {
  @Expose
  @Column(name="firstname")
  private String name;
  @Expose
  @Column(name="mobile")
  @Type(type="text")
  private String mobile;
  @Expose
  @Column(name="lastname")
  private String surname;
  @Expose
  @Transient
  private String email;
  @XStreamOmitField
  @Transient
  private String email3;
  @XStreamOmitField
  @Transient
  private String email2;
  @Expose
  @Transient
  private String adress;
  @XStreamOmitField
  @Transient
  private String adress2;
  @XStreamOmitField
  @Transient
  private String company;
  @XStreamOmitField
  @Transient
  private String group;
  @XStreamOmitField
  @Column(name="home")
  @Type(type="text")
  private String home;
  @XStreamOmitField
  @Column(name="work")
  @Type(type="text")
  private String work;
  @XStreamOmitField
  @Transient
  private String allPhones;
  @XStreamOmitField
  @Transient
  private String allEmails;
  @XStreamOmitField
  @Transient
  private String allAdresses;
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id;
  @XStreamOmitField

  @Column(name="photo")
  @Type(type="text")
  private String photo;



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

  public File getPhoto() { return new File(photo); }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }
}
