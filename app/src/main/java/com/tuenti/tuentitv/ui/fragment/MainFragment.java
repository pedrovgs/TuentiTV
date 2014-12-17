package com.tuenti.tuentitv.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.DisplayMetrics;
import android.view.View;
import com.squareup.picasso.Picasso;
import com.tuenti.tuentitv.R;
import com.tuenti.tuentitv.ui.activity.LoginActivity;
import com.tuenti.tuentitv.ui.activity.SearchActivity;
import com.tuenti.tuentitv.model.CardInfo;
import com.tuenti.tuentitv.ui.picasso.PicassoBackgroundManagerTarget;
import com.tuenti.tuentitv.ui.picasso.transformation.GrayScaleTransformation;
import com.tuenti.tuentitv.presenter.MainPresenter;
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

  private static final int UPDATE_BACKGROUND_IMAGE_DELAY_MILLIS = 700;
  private static final int FAVORITES_ROW = 1;
  private static final int CONVERSATIONS_ROW = 2;
  private static final int CONTACTS_ROW = 3;
  private static final int MEDIA_ROW = 4;

  @Inject MainPresenter presenter;

  private DisplayMetrics metrics;
  private PicassoBackgroundManagerTarget backgroundTarget;
  private Handler handler;
  private ChangeBackground lastChangeBackgroundRunnable;

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    this.handler = new Handler();
    presenter.setView(this);
    configureColors();
    configureApplicationIcon();
    prepareBackgroundManager();
    hookListeners();
    presenter.loadData();
  }

  @Override public void showDefaultBackground() {
    Picasso.with(getActivity())
        .load(R.drawable.main_fragment_default_background)
        .resize(metrics.widthPixels, metrics.heightPixels)
        .centerCrop()
        .into(backgroundTarget);
  }

  @Override public void updateBackground(String imageUrl) {
    if (lastChangeBackgroundRunnable != null) {
      handler.removeCallbacks(lastChangeBackgroundRunnable);
    }
    lastChangeBackgroundRunnable = new ChangeBackground(getActivity(), imageUrl, backgroundTarget);
    handler.postDelayed(lastChangeBackgroundRunnable, UPDATE_BACKGROUND_IMAGE_DELAY_MILLIS);
  }

  @Override public void showMainInformation(List<CardInfo> favorites, List<CardInfo> conversations,
      List<CardInfo> contacts, List<CardInfo> mediaElements) {
    ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
    CardPresenter cardPresenter = new CardPresenter();

    addElementsToRowsAdapter(R.string.favorites_item_title, favorites, rowsAdapter, cardPresenter,
        FAVORITES_ROW);
    addElementsToRowsAdapter(R.string.recent_conversation_item_title, conversations, rowsAdapter,
        cardPresenter, CONVERSATIONS_ROW);
    addElementsToRowsAdapter(R.string.contacts_item_title, contacts, rowsAdapter, cardPresenter,
        CONTACTS_ROW);
    addElementsToRowsAdapter(R.string.media_elements_item_title, mediaElements, rowsAdapter,
        cardPresenter, MEDIA_ROW);

    HeaderItem gridHeader = new HeaderItem(5, getResources().getString(R.string.preferences), null);

    GridItemPresenter gridPresenter = new GridItemPresenter();
    ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(gridPresenter);
    gridRowAdapter.add("Test1");
    gridRowAdapter.add("Test2");
    gridRowAdapter.add("Test3");
    rowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));

    ArrayObjectAdapter closeSessionAdapter = new ArrayObjectAdapter(new GridItemPresenter());
    closeSessionAdapter.add("Close session");
    HeaderItem header = new HeaderItem(6, getString(R.string.close_session), "");
    rowsAdapter.add(new ListRow(header, closeSessionAdapter));
    setAdapter(rowsAdapter);
  }

  @Override public void openSearchView() {
    Intent intent = new Intent(getActivity(), SearchActivity.class);
    startActivity(intent);
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
    setSearchAffordanceColor(getResources().getColor(R.color.primary_color_dark));
  }

  private void configureApplicationIcon() {
    setBadgeDrawable(getResources().getDrawable(R.drawable.icn_wink));
  }

  private void hookListeners() {
    configureOnItemSelectedListener();
    configureOnSearchClickedListener();
  }

  private void configureOnSearchClickedListener() {
    setOnSearchClickedListener(new View.OnClickListener() {

      @Override public void onClick(View view) {
        presenter.onSearchIconClicked();
      }
    });
  }

  private void configureOnItemSelectedListener() {
    setOnItemViewSelectedListener(new OnItemViewSelectedListener() {
      @Override public void onItemSelected(Presenter.ViewHolder viewHolder, Object item,
          RowPresenter.ViewHolder viewHolder1, Row row) {
        if (row.getId() <= MEDIA_ROW) {
          presenter.onCardInfoSelected((CardInfo) item);
        } else if (row.getId() == 5) {
          presenter.onPreferencesSelected();
        }
      }
    });
    setOnItemViewClickedListener(new OnItemViewClickedListener() {
      @Override public void onItemClicked(Presenter.ViewHolder viewHolder, Object o,
          RowPresenter.ViewHolder viewHolder1, Row row) {
        if (row.getId() == 6) {
          startActivity(new Intent(getActivity(), LoginActivity.class));
          getActivity().finish();
        }
      }
    });
  }

  private static class ChangeBackground implements Runnable {

    private final Context context;
    private String photo;
    private final PicassoBackgroundManagerTarget backgroundTarget;

    public ChangeBackground(Context context, String photo,
        PicassoBackgroundManagerTarget backgroundTarget) {
      this.context = context;
      this.photo = photo;
      this.backgroundTarget = backgroundTarget;
    }

    @Override public void run() {
      Picasso.with(context).cancelRequest(backgroundTarget);
      Picasso.with(context)
          .load(photo)
          .placeholder(R.drawable.main_fragment_default_background)
          .transform(new GrayScaleTransformation(Picasso.with(context)))
          .into(backgroundTarget);
    }
  }
}
