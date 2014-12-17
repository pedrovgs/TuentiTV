package com.github.pedrovgs.tuentitv.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnItemClickedListener;
import android.support.v17.leanback.widget.Row;
import android.text.TextUtils;
import android.util.Log;

/**
 * Search fragment created to support search functionality.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class SearchFragment extends android.support.v17.leanback.app.SearchFragment
    implements android.support.v17.leanback.app.SearchFragment.SearchResultProvider {
  private static final String TAG = "SearchFragment";
  private static final int SEARCH_DELAY_MS = 300;

  private ArrayObjectAdapter mRowsAdapter;
  private Handler mHandler = new Handler();
  private SearchRunnable mDelayedLoad;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
    setSearchResultProvider(this);
    setOnItemClickedListener(getDefaultItemClickedListener());
    mDelayedLoad = new SearchRunnable();
  }

  @Override public ObjectAdapter getResultsAdapter() {
    return mRowsAdapter;
  }

  private void queryByWords(String words) {
    mRowsAdapter.clear();
    if (!TextUtils.isEmpty(words)) {
      mDelayedLoad.setSearchQuery(words);
      mHandler.removeCallbacks(mDelayedLoad);
      mHandler.postDelayed(mDelayedLoad, SEARCH_DELAY_MS);
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

