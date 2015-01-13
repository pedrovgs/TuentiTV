/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.tuentitv.ui.fragment;

import android.content.Context;
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
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.presenter.MainPresenter;
import com.github.pedrovgs.tuentitv.ui.data.CardInfo;
import com.github.pedrovgs.tuentitv.ui.data.IconInfo;
import com.github.pedrovgs.tuentitv.ui.data.ImageInfo;
import com.github.pedrovgs.tuentitv.ui.picasso.PicassoBackgroundManagerTarget;
import com.github.pedrovgs.tuentitv.ui.picasso.transformation.GrayScaleTransformation;
import com.github.pedrovgs.tuentitv.ui.viewpresenter.CardPresenter;
import com.github.pedrovgs.tuentitv.ui.viewpresenter.IconPresenter;
import com.github.pedrovgs.tuentitv.ui.viewpresenter.ImagePresenter;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;

/**
 * BrowseFragment extension created to work as main application fragment and to show most important
 * information related to the user. Favorite contacts, contacts, recent conversation, media gallery
 * elements and other information like application preferences.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainFragment extends BrowseBaseFragment implements MainPresenter.View {

  private static final int UPDATE_BACKGROUND_IMAGE_DELAY_MILLIS = 700;
  private static final int FAVORITES_ROW = 1;
  private static final int CONVERSATIONS_ROW = 2;
  private static final int CONTACTS_ROW = 3;
  private static final int MEDIA_ROW = 4;
  private static final int PREFERENCES_ROW = 5;

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
        .load(R.drawable.fragment_default_background)
        .placeholder(R.drawable.fragment_default_background)
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
      List<CardInfo> contacts, List<ImageInfo> mediaElements, List<IconInfo> preferences) {
    ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
    CardPresenter cardPresenter = new CardPresenter();

    addCardInfoElementsToRowsAdapter(R.string.favorites_item_title, favorites, rowsAdapter,
        cardPresenter, FAVORITES_ROW);
    addCardInfoElementsToRowsAdapter(R.string.recent_conversation_item_title, conversations,
        rowsAdapter, cardPresenter, CONVERSATIONS_ROW);
    addCardInfoElementsToRowsAdapter(R.string.contacts_item_title, contacts, rowsAdapter,
        cardPresenter, CONTACTS_ROW);
    addImageInfoElementsToRowAdapter(R.string.media_elements_item_title, mediaElements, rowsAdapter,
        new ImagePresenter(), MEDIA_ROW);
    addIconInfoElementsToRowAdapter(getResources().getString(R.string.preferences), preferences,
        rowsAdapter, new IconPresenter(), PREFERENCES_ROW);

    setAdapter(rowsAdapter);
  }

  @Override public void closeView() {
    getActivity().finish();
  }

  @Override public void cancelPendingBackgroundUpdates() {
    Picasso.with(getActivity()).cancelRequest(backgroundTarget);
    handler.removeCallbacks(lastChangeBackgroundRunnable);
  }

  private void addCardInfoElementsToRowsAdapter(int title, List<CardInfo> elements,
      ArrayObjectAdapter rowsAdapter, Presenter presenter, int id) {
    ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(presenter);
    for (Object element : elements) {
      listRowAdapter.add(element);
    }
    HeaderItem header = new HeaderItem(id, getString(title), null);
    rowsAdapter.add(new ListRow(header, listRowAdapter));
  }

  private void addImageInfoElementsToRowAdapter(int title, List<ImageInfo> elements,
      ArrayObjectAdapter rowsAdapter, Presenter presenter, int id) {
    ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(presenter);
    for (Object element : elements) {
      listRowAdapter.add(element);
    }
    HeaderItem header = new HeaderItem(id, getString(title), null);
    rowsAdapter.add(new ListRow(header, listRowAdapter));
  }

  private void addIconInfoElementsToRowAdapter(String title, List<IconInfo> preferences,
      ArrayObjectAdapter rowsAdapter, Presenter presenter, int id) {
    ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(presenter);
    for (IconInfo iconInfo : preferences) {
      listRowAdapter.add(iconInfo);
    }
    rowsAdapter.add(new ListRow(new HeaderItem(id, title, ""), listRowAdapter));
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
        if (row.getId() < MEDIA_ROW) {
          presenter.onCardInfoSelected((CardInfo) item);
        } else if (row.getId() == MEDIA_ROW) {
          presenter.onImageInfoSelected((ImageInfo) item);
        } else if (row.getId() == PREFERENCES_ROW) {
          presenter.onPreferencesSelected();
        }
      }
    });
    setOnItemViewClickedListener(new OnItemViewClickedListener() {
      @Override public void onItemClicked(Presenter.ViewHolder viewHolder, Object item,
          RowPresenter.ViewHolder viewHolder1, Row row) {
        if (row.getId() == PREFERENCES_ROW) {
          int id = ((IconInfo) item).getIconId();
          switch (id) {
            case R.drawable.icn_settings_log_out:
              presenter.logout();
              break;
            default:
          }
        } else if (row.getId() == MEDIA_ROW) {
          presenter.onImageInfoClicked((ImageInfo) item);
        } else if (row.getId() < MEDIA_ROW) {
          presenter.onCardInfoClicked((CardInfo) item);
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
          .placeholder(R.drawable.fragment_default_background)
          .transform(new GrayScaleTransformation())
          .into(backgroundTarget);
    }
  }
}
