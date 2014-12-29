package com.github.pedrovgs.tuentitv.ui.fragment;

import android.os.Bundle;
import com.github.pedrovgs.tuentitv.presenter.DetailPresenter;
import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DetailFragment extends DetailBaseFragment implements DetailPresenter.View {

  public static final String ID_EXTRA = "id_extra";

  @Inject DetailPresenter presenter;

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    presenter.setView(this);
    String contentId = getContentId();
    presenter.loadContent(contentId);
  }

  private String getContentId() {
    return getActivity().getIntent().getExtras().getString(ID_EXTRA);
  }
}
