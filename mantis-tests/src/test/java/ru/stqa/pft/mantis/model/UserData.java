package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "username")
  private String userName;

  @Column(name = "email")
  private String email;

  @Column(name = "enabled")
  private Byte enabled;

  @Column(name = "access_level")
  private short accessLevel;

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public UserData withUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public boolean getEnabled() {
    return enabled.equals("1");
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = Byte.valueOf(enabled.toString());
  }

  public int getAccessLevel() {
    return this.accessLevel;
  }

  @Override
  public String toString() {
    return "UserData{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", email='" + email + '\'' +
        ", enabled=" + enabled +
        ", access_level=" + accessLevel +
        '}';
  }

  public UserData withAccessLevel(int accessLevel) {
    this.accessLevel = (short) accessLevel;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
        userName.equals(userData.userName) &&
        email.equals(userData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, email);
  }


}
