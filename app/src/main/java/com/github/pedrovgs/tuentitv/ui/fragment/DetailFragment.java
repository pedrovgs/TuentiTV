package com.github.pedrovgs.tuentitv.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.util.DisplayMetrics;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.presenter.DetailPresenter;
import com.github.pedrovgs.tuentitv.ui.picasso.PicassoBackgroundManagerTarget;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DetailFragment extends DetailBaseFragment implements DetailPresenter.View {

  public static final String ID_EXTRA = "id_extra";

  @Inject DetailPresenter presenter;

  private PicassoBackgroundManagerTarget backgroundTarget;
  private Drawable defaultBackground;
  private DisplayMetrics metrics;

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    configureBackground();
    configureDetailPresenter();
  }

  @Override public void showBackground(String backgroundUrl) {
    Picasso.with(getActivity())
        .load(backgroundUrl)
        .resize(metrics.widthPixels, metrics.heightPixels)
        .placeholder(defaultBackground)
        .error(defaultBackground)
        .into(backgroundTarget);
  }

  private void configureBackground() {
    metrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
    BackgroundManager backgroundManager = BackgroundManager.getInstance(getActivity());
    backgroundManager.attach(getActivity().getWindow());
    backgroundTarget = new PicassoBackgroundManagerTarget(backgroundManager);
    defaultBackground = getResources().getDrawable(R.drawable.fragment_default_background);
  }

  private void configureDetailPresenter() {
    presenter.setView(this);
    String contentId = getContentId();
    presenter.loadContent(contentId);
  }

  private String getContentId() {
    return getActivity().getIntent().getExtras().getString(ID_EXTRA);
  }
}
