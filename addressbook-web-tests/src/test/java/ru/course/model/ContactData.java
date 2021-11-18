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
//  private String name = "";
  @Expose
  @Column(name="mobile")
  @Type(type="text")
  private String mobile;
//  private String mobile = "";
  @Expose
  @Column(name="lastname")
  private String surname;
  @Expose
  @Column(name="email")
  @Type(type="text")
  private String email;
//  private String email = "";
  @XStreamOmitField
  @Transient
  private String email3;
  @XStreamOmitField
  @Transient
  private String email2;
  @Expose
  @Column(name="address")
  @Type(type="text")
  private String adress;
//  private String adress = "";
  @XStreamOmitField
  @Transient
  private String adress2;
  @XStreamOmitField
  @Column(name="company")
  private String company;
//  private String company = "";
  @XStreamOmitField
  @Transient
  private String group;
  @XStreamOmitField
  @Column(name="home")
  @Type(type="text")
  private String home;
//  private String home = "";
  @XStreamOmitField
  @Column(name="work")
  @Type(type="text")
  private String work;
//  private String work = "";
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    return work != null ? work.equals(that.work) : that.work == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (adress != null ? adress.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (home != null ? home.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + id;
    return result;
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
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", mobile='" + mobile + '\'' +
            ", surname='" + surname + '\'' +
            ", email='" + email + '\'' +
            ", adress='" + adress + '\'' +
            ", company='" + company + '\'' +
            ", group='" + group + '\'' +
            ", home='" + home + '\'' +
            ", work='" + work + '\'' +
            ", id=" + id +
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

  public File getPhoto() {
    if (photo != null) return new File(photo);
    return null;}

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData (ContactData contact)
  {
    this.id = contact.id;
    this.name = contact.name;
    this.surname = contact.surname;
    this.mobile = contact.mobile;
    this.work = contact.work;
    this.company = contact.company;
    this.adress = contact.adress;
    this.adress2 = contact.adress2;
    this.allAdresses = contact.allAdresses;
    this.email2 = contact.email2;
    this.email3 = contact.email3;
    this.home = contact.home;
    this.group = contact.group;
    this.allEmails = contact.allEmails;
    this.allPhones = contact.allPhones;
//    this.photo = contact.photo;

  }
  public ContactData ()
  {

  }

}
