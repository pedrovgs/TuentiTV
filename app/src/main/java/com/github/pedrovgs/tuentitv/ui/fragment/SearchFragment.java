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

import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnItemClickedListener;
import android.support.v17.leanback.widget.Row;
import android.text.TextUtils;
import android.util.Log;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.model.Contact;
import com.github.pedrovgs.tuentitv.presenter.SearchPresenter;
import com.github.pedrovgs.tuentitv.ui.viewpresenter.CardPresenter;
import java.util.List;
import javax.inject.Inject;

/**
 * Search fragment and SearchFragment extension created to support search functionality for
 * contacts.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class SearchFragment extends SearchBaseFragment
    implements android.support.v17.leanback.app.SearchFragment.SearchResultProvider,
    SearchPresenter.View {

  private static final String TAG = "SearchFragment";
  private static final int SEARCH_DELAY_MS = 300;

  @Inject SearchPresenter searchPresenter;

  private ArrayObjectAdapter rowsAdapter;
  private Handler handler = new Handler();
  private SearchRunnable delayedLoad;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
    setSearchResultProvider(this);
    setOnItemClickedListener(getDefaultItemClickedListener());
    delayedLoad = new SearchRunnable();
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    searchPresenter.setView(this);
    loadAllContacts();
  }

  @Override public ObjectAdapter getResultsAdapter() {
    return rowsAdapter;
  }

  @Override public boolean onQueryTextChange(String newQuery) {
    Log.d(TAG, String.format("Search Query Text Change %s", newQuery));
    queryByWords(newQuery);
    return true;
  }

  @Override public boolean onQueryTextSubmit(String query) {
    Log.d(TAG, String.format("Search Query Text Submit %s", query));
    queryByWords(query);
    return true;
  }

  @Override public void showAllContacts(List<Contact> contacts) {
    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new CardPresenter());
    for (Contact contact : contacts) {
      arrayObjectAdapter.add(contact);
    }
    HeaderItem headerItem = new HeaderItem(getString(R.string.contacts_item_title), "");
    rowsAdapter.add(new ListRow(headerItem, arrayObjectAdapter));
  }

  @Override public void showSearchResultContacts(String query, List<Contact> contacts) {
    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new CardPresenter());
    for (Contact contact : contacts) {
      arrayObjectAdapter.add(contact);
    }
    String resultSearchTitle = getString(R.string.search_result, query);
    HeaderItem headerItem = new HeaderItem(resultSearchTitle, "");
    rowsAdapter.add(new ListRow(headerItem, arrayObjectAdapter));
  }

  @Override public void finish() {
    getActivity().finish();
  }

  protected OnItemClickedListener getDefaultItemClickedListener() {
    return new OnItemClickedListener() {
      @Override public void onItemClicked(Object item, Row row) {
        searchPresenter.onContactClicked((Contact) item);
      }
    };
  }

  private void loadRows(String query) {
    loadContactsMatchingQuery(query);
    loadAllContacts();
  }

  private void loadAllContacts() {
    searchPresenter.loadContacts();
  }

  private void loadContactsMatchingQuery(String query) {
    searchPresenter.searchContacts(query);
  }

  private void queryByWords(String words) {
    rowsAdapter.clear();
    if (!TextUtils.isEmpty(words)) {
      delayedLoad.setSearchQuery(words);
      handler.removeCallbacks(delayedLoad);
      handler.postDelayed(delayedLoad, SEARCH_DELAY_MS);
    } else {
      loadAllContacts();
    }
  }

  private class SearchRunnable implements Runnable {

    private volatile String searchQuery;

    public SearchRunnable() {
    }

    public void run() {
      loadRows(searchQuery);
    }

    public void setSearchQuery(String value) {
      this.searchQuery = value;
    }
  }
}

