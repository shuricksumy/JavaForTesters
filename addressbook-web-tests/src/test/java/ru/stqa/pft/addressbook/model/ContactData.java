package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = "middlename")
  private String middleName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Expose
  @Column(name = "nickname")
  private String nickname;

  @Expose
  @Column(name = "title")
  private String title;

  @Expose
  @Column(name = "company")
  private String companyName;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String emailAddressFirst;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String emailAddressSecond;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String emailAddressThird;

  @Expose
  @Transient
  private String emailAddressAll;

  @Expose
  @Column(name = "homepage")
  @Type(type = "text")
  private String homeSite;

  @XStreamOmitField
  @Transient
  private DateData birthday;

  @XStreamOmitField
  @Id
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
      joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();

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

  public File getPhoto() {
    return new File(photo);
  }

  public Groups getGroups() {
    if (groups != null) {
      return new Groups(groups);
    }
    return null;
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

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withGroups(GroupData group) {
    groups.add(group);
    return this;
  }

  public ContactData withoutGroups(GroupData group) {
    groups.remove(group);
    return this;
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
        ", groups=" + groups +
        '}';
  }
}
