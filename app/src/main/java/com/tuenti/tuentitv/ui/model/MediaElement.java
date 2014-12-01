package com.tuenti.tuentitv.ui.model;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MediaElement implements CardInfo {

  private final String title;
  private final String imageUrl;
  private final String secondaryImage;

  public MediaElement(String title, String imageUrl,String secondaryImage) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.secondaryImage = secondaryImage;
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
    return secondaryImage;
  }
}
