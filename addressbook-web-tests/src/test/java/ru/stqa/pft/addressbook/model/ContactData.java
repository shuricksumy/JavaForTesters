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
  private String homePhone;
  private String workPhone;
  private String allPhones;
  private String emailAddressFirst;
  private String emailAddressSecond;
  private String emailAddressThird;
  private String emailAddressAll;
  private String homeSite;
  private DateData birthday;
  private int id = Integer.MAX_VALUE;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
        Objects.equals(firstName, that.firstName) &&
        Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, id);
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

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() {
    return allPhones;
  }


  public String getEmailAddressFirst() {
    return emailAddressFirst;
  }

  public String getEmailAddressSecond() {
    return emailAddressSecond;
  }

  public String getEmailAddressThird() {
    return emailAddressThird;
  }

  public String getEmailAddressAll() {
    return emailAddressAll;
  }

  public String getHomeSite() {
    return homeSite;
  }

  public DateData getBirthday() {
    return birthday;
  }

  @Override
  public String toString() {
    return "ContactData{" +
        "firstName='" + firstName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", nickname='" + nickname + '\'' +
        ", title='" + title + '\'' +
        ", companyName='" + companyName + '\'' +
        ", address='" + address + '\'' +
        ", mobilePhone='" + mobilePhone + '\'' +
        ", homePhone='" + homePhone + '\'' +
        ", workPhone='" + workPhone + '\'' +
        ", allPhones='" + allPhones + '\'' +
        ", emailAddressFirst='" + emailAddressFirst + '\'' +
        ", emailAddressSecond='" + emailAddressSecond + '\'' +
        ", emailAddressThird='" + emailAddressThird + '\'' +
        ", emailAddressAll='" + emailAddressAll + '\'' +
        ", homeSite='" + homeSite + '\'' +
        ", birthday=" + birthday +
        ", id=" + id +
        '}';
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

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmailAddressFirst(String emailAddressFirst) {
    this.emailAddressFirst = emailAddressFirst;
    return this;
  }

  public ContactData withEmailAddressSecond(String emailAddressSecond) {
    this.emailAddressSecond = emailAddressSecond;
    return this;
  }

  public ContactData withEmailAddressThird(String emailAddressThird) {
    this.emailAddressThird = emailAddressThird;
    return this;
  }

  public ContactData withEmailAddressAll(String emailAddressAll) {
    this.emailAddressAll = emailAddressAll;
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

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
}
