package com.tuenti.tuentitv.ui.fragment;

import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.util.DisplayMetrics;
import com.squareup.picasso.Picasso;
import com.tuenti.tuentitv.R;
import com.tuenti.tuentitv.ui.picasso.PicassoBackgroundManagerTarget;
import com.tuenti.tuentitv.ui.presenter.MainPresenter;
import javax.inject.Inject;

/**
 * Main application fragment used to show most important information related to the user.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainFragment extends BrowseBaseFragment implements MainPresenter.View {

  @Inject MainPresenter presenter;

  private DisplayMetrics metrics;
  private PicassoBackgroundManagerTarget backgroundTarget;

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    presenter.setView(this);
    prepareBackgroundManager();
    configureColors();
    configureApplicationIcon();
    updateBackground("https://tuentiassets3-a.akamaihd.net/AADYA-oRVco");
  }

  private void prepareBackgroundManager() {
    BackgroundManager backgroundManager = BackgroundManager.getInstance(getActivity());
    backgroundManager.attach(getActivity().getWindow());
    backgroundTarget = new PicassoBackgroundManagerTarget(backgroundManager);
    metrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
  }

  private void configureColors() {
    setBrandColor(getResources().getColor(R.color.primary_color));
    setSearchAffordanceColor(getResources().getColor(R.color.secondary_color));
  }

  private void configureApplicationIcon() {
    setBadgeDrawable(getResources().getDrawable(R.drawable.icn_application_transparent_bg));
  }

  private void updateBackground(String imageUrl) {
    Picasso.with(getActivity())
        .load(imageUrl)
        .resize(metrics.widthPixels, metrics.heightPixels)
        .centerCrop()
        .into(backgroundTarget);
  }
}
