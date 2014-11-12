package com.tuenti.tuentitv.ui.fragment;

import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import com.tuenti.tuentitv.ui.androidpresenter.AccountPresenter;

/**
 * Fragment shown to the user inside LoginActivity with a set of already added accounts. This is
 * going to be the fragment shown to the user if there is no other user authenticated.
 *
 * This fragment extends from BrowseFragment and we have to configure this Android Leanback
 * component properly.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class LoginFragment extends BrowseBaseFragment {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Remove left fragment shown as main menu for BrowseFragments
    setHeadersState(HEADERS_DISABLED);

    //Show information account in a single row.
    ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
    AccountPresenter accountPresenter = new AccountPresenter();

    ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(accountPresenter);
    //listRowAdapter.add(new Account(name, avatarUrl));

    //Header associated to the row, in this fragment, an empty header.
    HeaderItem header = new HeaderItem("", null);
    rowsAdapter.add(new ListRow(header, listRowAdapter));
    setAdapter(rowsAdapter);
  }
}
