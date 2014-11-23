package com.tuenti.tuentitv.ui.model;

/**
 * Class created to represent a user contact. One contact has a name and one avatar.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Contact implements CardInfo {

  private final String name;
  private final String avatarUrl;

  public Contact(String name, String avatarUrl) {
    this.name = name;
    this.avatarUrl = avatarUrl;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public String getName() {
    return name;
  }

  @Override public String getCardImageUrl() {
    return getAvatarUrl();
  }

  @Override public String getTitle() {
    return getName();
  }

  @Override public String getText() {
    return null;
  }
}
