package com.github.pedrovgs.tuentitv.ui.data;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class IconInfo {

  private final String title;
  private final int iconId;

  public IconInfo(String title, int iconId) {
    this.title = title;
    this.iconId = iconId;
  }

  public String getTitle() {
    return title;
  }

  public int getIconId() {
    return iconId;
  }
}
