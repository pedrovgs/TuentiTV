package com.github.pedrovgs.tuentitv.model;

import com.github.pedrovgs.tuentitv.ui.data.CardInfo;

/**
 * Class created to represent a user contact. One contact has a name and one avatar.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Contact implements CardInfo {

  private final String name;
  private final String avatarUrl;
  private final String secondaryImage;

  public Contact(String name, String avatarUrl, String secondaryImage) {
    this.name = name;
    this.avatarUrl = avatarUrl;
    this.secondaryImage = secondaryImage;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public String getName() {
    return name;
  }

  @Override public String getId() {
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

  @Override public String getSecondaryImage() {
    return secondaryImage;
  }
}
