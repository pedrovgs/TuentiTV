package com.tuenti.tuentitv.domain;

/**
 * Class created to represent user accounts. One user account user name and user avatar photo url.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Account {

  private final String name;
  private final String avatarUrl;

  public Account(String name, String avatarUrl) {
    this.name = name;
    this.avatarUrl = avatarUrl;
  }

  public String getName() {
    return name;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }
}
