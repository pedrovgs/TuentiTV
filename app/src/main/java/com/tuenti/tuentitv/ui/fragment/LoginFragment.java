package com.tuenti.tuentitv.ui.fragment;

import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import com.tuenti.tuentitv.ui.model.Account;
import com.tuenti.tuentitv.ui.androidpresenter.AccountPresenter;
import com.tuenti.tuentitv.ui.presenter.LoginViewPresenter;
import java.util.List;
import javax.inject.Inject;

/**
 * Fragment shown to the user inside LoginActivity with a set of already added accounts. This is
 * going to be the fragment shown to the user if there is no other user authenticated.
 *
 * This fragment extends from BrowseFragment and we have to configure this Android Leanback
 * component properly.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class LoginFragment extends BrowseBaseFragment implements LoginViewPresenter.View {

  @Inject LoginViewPresenter presenter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    presenter.setView(this);
    presenter.loadAccounts();

    // Remove left fragment shown as main menu for BrowseFragments
    setHeadersState(HEADERS_DISABLED);
  }

  @Override public void showAccounts(List<Account> accounts) {
    //Show information account in a single row.
    ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
    AccountPresenter accountPresenter = new AccountPresenter();

    //Add accounts loaded to accounts adapter.
    ArrayObjectAdapter accountsAdapter = new ArrayObjectAdapter(accountPresenter);
    accountsAdapter.addAll(0, accounts);

    //Header associated to the row, in this fragment, an empty header.
    HeaderItem header = new HeaderItem("", null);
    rowsAdapter.add(new ListRow(header, accountsAdapter));
    setAdapter(rowsAdapter);
  }
}
