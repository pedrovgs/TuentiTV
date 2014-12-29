package com.github.pedrovgs.tuentitv.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.DetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.presenter.DetailPresenter;
import com.github.pedrovgs.tuentitv.presenter.DetailsDescriptionPresenter;
import com.github.pedrovgs.tuentitv.ui.data.CardInfo;
import com.github.pedrovgs.tuentitv.ui.picasso.PicassoBackgroundManagerTarget;
import com.github.pedrovgs.tuentitv.ui.util.Util;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DetailFragment extends DetailBaseFragment implements DetailPresenter.View {

  public static final String ID_EXTRA = "id_extra";

  private static final long VD_CALL_ACTION_ID = 1;
  private static final long CALL_ACTION_ID = 2;
  private static final long CHAT_ACTION_ID = 3;
  private static final int DETAIL_THUMB_WIDTH = 274;
  private static final int DETAIL_THUMB_HEIGHT = 274;

  @Inject DetailPresenter presenter;

  private PicassoBackgroundManagerTarget backgroundTarget;
  private Drawable defaultBackground;
  private DisplayMetrics metrics;
  private ArrayObjectAdapter adapter;

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

  @Override public void showCardInfo(CardInfo cardInfo) {
    DetailsOverviewRow detailRow = configureDetailsOverviewRow(cardInfo);
    ClassPresenterSelector presenterSelector = new ClassPresenterSelector();
    DetailsOverviewRowPresenter dorPresenter =
        new DetailsOverviewRowPresenter(new DetailsDescriptionPresenter());
    dorPresenter.setBackgroundColor(getResources().getColor(R.color.primary_color));
    dorPresenter.setStyleLarge(false);
    presenterSelector.addClassPresenter(DetailsOverviewRow.class, dorPresenter);
    presenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
    adapter = new ArrayObjectAdapter(presenterSelector);
    adapter.add(detailRow);
    setAdapter(adapter);
  }

  private DetailsOverviewRow configureDetailsOverviewRow(CardInfo cardInfo) {
    final DetailsOverviewRow row = new DetailsOverviewRow(cardInfo);
    Picasso.with(getActivity())
        .load(cardInfo.getCardImageUrl())
        .placeholder(R.drawable.icn_wink)
        .error(R.drawable.icn_wink)
        .resize(Util.convertDpToPixel(getActivity(), DETAIL_THUMB_WIDTH),
            Util.convertDpToPixel(getActivity(), DETAIL_THUMB_HEIGHT))
        .centerCrop()
        .into(new Target() {
          @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Log.e("DEPURAR", "IMAGEN CARGADA!!!");
            row.setImageScaleUpAllowed(true);
            row.setImageBitmap(getActivity(), bitmap);
            setAdapter(adapter);
          }

          @Override public void onBitmapFailed(Drawable errorDrawable) {
            Log.e("DEPURAR", "BITMAP FAILED!!!");
            row.setImageScaleUpAllowed(false);
            row.setImageDrawable(errorDrawable);
            setAdapter(adapter);
          }

          @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
            Log.e("DEPURAR", "PREPARING LOAD!!!");
            row.setImageScaleUpAllowed(false);
            row.setImageDrawable(placeHolderDrawable);
            setAdapter(adapter);
          }
        });
    row.addAction(new Action(VD_CALL_ACTION_ID, getString(R.string.vd_call_action_title)));
    row.addAction(new Action(CALL_ACTION_ID, getString(R.string.call_action_title)));
    row.addAction(new Action(CHAT_ACTION_ID, getString(R.string.chat_action_title)));
    return row;
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
