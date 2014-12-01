package com.tuenti.tuentitv.ui.model;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MediaElement implements CardInfo {

  private final String title;
  private final String imageUrl;

  public MediaElement(String title, String imageUrl) {
    this.title = title;
    this.imageUrl = imageUrl;
  }

  @Override public String getCardImageUrl() {
    return imageUrl;
  }

  @Override public String getTitle() {
    return title;
  }

  @Override public String getText() {
    return null;
  }

  @Override public String getSecondaryImage() {
    return imageUrl;
  }
}
