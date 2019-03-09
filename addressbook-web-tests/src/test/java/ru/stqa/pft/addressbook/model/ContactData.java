package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickname;
  private final String title;
  private final String companyName;
  private final String address;
  private final String mobilePhone;
  private final String emailAddress;
  private final String homeSite;
  private final DateData birthday;

  public ContactData(String firstName, String middleName, String lastName, String nickname, String title, String companyName, String address, String mobilePhone, String emailAddress, String homeSite, DateData birthday) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.title = title;
    this.companyName = companyName;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.emailAddress = emailAddress;
    this.homeSite = homeSite;
    this.birthday = birthday;
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
}
