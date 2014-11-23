package com.tuenti.tuentitv.ui.fragment;

import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.util.DisplayMetrics;
import com.squareup.picasso.Picasso;
import com.tuenti.tuentitv.R;
import com.tuenti.tuentitv.ui.model.CardInfo;
import com.tuenti.tuentitv.ui.picasso.PicassoBackgroundManagerTarget;
import com.tuenti.tuentitv.ui.presenter.MainPresenter;
import com.tuenti.tuentitv.ui.viewpresenter.CardPresenter;
import com.tuenti.tuentitv.ui.viewpresenter.GridItemPresenter;
import java.util.List;
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
    presenter.loadData();
  }

  @Override public void updateBackground(String imageUrl) {
    Picasso.with(getActivity())
        .load(imageUrl)
        .resize(metrics.widthPixels, metrics.heightPixels)
        .centerCrop()
        .into(backgroundTarget);
  }

  @Override public void showMainInformation(List<CardInfo> favorites, List<CardInfo> conversations,
      List<CardInfo> contacts) {
    ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
    CardPresenter cardPresenter = new CardPresenter();

    addElementsToRowsAdapter(R.string.favorites_item_title, favorites, rowsAdapter, cardPresenter,
        1);
    addElementsToRowsAdapter(R.string.recent_conversation_item_title, conversations, rowsAdapter,
        cardPresenter, 2);
    addElementsToRowsAdapter(R.string.contacts_item_title, contacts, rowsAdapter, cardPresenter, 3);

    HeaderItem gridHeader = new HeaderItem(4, getResources().getString(R.string.preferences), null);

    GridItemPresenter gridPresenter = new GridItemPresenter();
    ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(gridPresenter);
    gridRowAdapter.add("Test1");
    gridRowAdapter.add("Test2");
    gridRowAdapter.add("Test3");
    rowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));

    setAdapter(rowsAdapter);
  }

  private void addElementsToRowsAdapter(int title, List<CardInfo> elements,
      ArrayObjectAdapter rowsAdapter, CardPresenter cardPresenter, int id) {
    ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);
    for (CardInfo cardInfo : elements) {
      listRowAdapter.add(cardInfo);
    }
    HeaderItem header = new HeaderItem(id, getString(title), null);
    rowsAdapter.add(new ListRow(header, listRowAdapter));
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
}
