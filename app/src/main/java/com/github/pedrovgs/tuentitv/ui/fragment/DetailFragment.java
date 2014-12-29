package com.github.pedrovgs.tuentitv.ui.fragment;

import android.support.v17.leanback.app.DetailsFragment;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DetailFragment extends DetailsFragment {

  private String contentId;

  public void setContentId(String contentId) {
    this.contentId = contentId;
  }
}
