package com.github.pedrovgs.tuentitv.ui.data;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class IconInfo {

  private final int title;
  private final int iconId;

  public IconInfo(int title, int iconId) {
    this.title = title;
    this.iconId = iconId;
  }

  public int getTitle() {
    return title;
  }

  public int getIconId() {
    return iconId;
  }
}
