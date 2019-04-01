package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private String firstName;
  private String middleName;
  private String lastName;
  private String nickname;
  private String title;
  private String companyName;
  private String address;
  private String mobilePhone;
  private String emailAddress;
  private String homeSite;
  private DateData birthday;
  private int id = Integer.MAX_VALUE;
  ;

  @Override
  public String toString() {
    return "ContactData{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) &&
        Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getHomeSite() {
    return homeSite;
  }

  public DateData getBirthday() {
    return birthday;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  public ContactData withHomeSite(String homeSite) {
    this.homeSite = homeSite;
    return this;
  }

  public ContactData withBirthday(DateData birthday) {
    this.birthday = birthday;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
}
