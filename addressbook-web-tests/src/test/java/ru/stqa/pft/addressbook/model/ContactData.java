package ru.stqa.pft.addressbook.model;

import java.util.Objects;

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
        this.id = Integer.MAX_VALUE;
    }

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

    public ContactData(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.middleName = null;
        this.lastName = lastName;
        this.nickname = null;
        this.title = null;
        this.companyName = null;
        this.address = null;
        this.mobilePhone = null;
        this.emailAddress = null;
        this.homeSite = null;
        this.birthday = null;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public ContactData(String firstName, String middleName, String lastName, String nickname, String title, String companyName, String address, String mobilePhone, String emailAddress, String homeSite, DateData birthday, int id) {
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
        this.id = id;
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
