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
import com.github.pedrovgs.tuentitv.model.Agenda;
import com.github.pedrovgs.tuentitv.model.Contact;
import com.github.pedrovgs.tuentitv.ui.viewpresenter.CardPresenter;
import com.tuenti.tuentitv.R;
import java.util.List;
import javax.inject.Inject;

/**
 * Search fragment created to support search functionality.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class SearchFragment extends SearchBaseFragment
    implements android.support.v17.leanback.app.SearchFragment.SearchResultProvider {

  private static final String TAG = "SearchFragment";
  private static final int SEARCH_DELAY_MS = 300;

  @Inject Agenda agenda;

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
    loadAllContacts();
  }

  @Override public ObjectAdapter getResultsAdapter() {
    return rowsAdapter;
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

  private void loadRows(String query) {
    loadContactsMatchingQuery(query);
    loadAllContacts();
  }

  private void loadAllContacts() {
    List<Contact> contacts = agenda.getContacts();
    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new CardPresenter());
    for (Contact contact : contacts) {
      arrayObjectAdapter.add(contact);
    }
    HeaderItem headerItem = new HeaderItem(getString(R.string.contacts_item_title), "");
    rowsAdapter.add(new ListRow(headerItem, arrayObjectAdapter));
  }

  private void loadContactsMatchingQuery(String query) {
    HeaderItem queryHeaderItem = new HeaderItem(query, "");
    query = query.toLowerCase();
    List<Contact> contacts = agenda.getContactsWithName(query);
    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new CardPresenter());
    for (Contact contact : contacts) {
      arrayObjectAdapter.add(contact);
    }
    rowsAdapter.add(new ListRow(queryHeaderItem, arrayObjectAdapter));
  }

  protected OnItemClickedListener getDefaultItemClickedListener() {
    return new OnItemClickedListener() {
      @Override public void onItemClicked(Object item, Row row) {

      }
    };
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

