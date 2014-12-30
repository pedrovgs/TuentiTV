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
package com.github.pedrovgs.tuentitv.presenter;

import com.github.pedrovgs.tuentitv.model.Account;
import com.github.pedrovgs.tuentitv.model.Accounts;
import java.util.List;
import javax.inject.Inject;

/**
 * Class created to work as login view presenter. This presenter has all the responsibility related
 * to the login view presentation logic.
 *
 * Responsibilities:
 *
 * - Obtains a list of accounts previously logged and shows it. For this sample we are going to
 * mock
 * all this information with fake accounts.
 *
 * - If the user clicks on one account is going to show the password dialog. If the user enters his
 * password this presenter is going to start next view.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class LoginPresenter {

  private final Accounts accounts;
  private View view;
  private List<Account> accountList;
  private Account selectedAccount;

  @Inject public LoginPresenter(Accounts accounts) {
    this.accounts = accounts;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void initialize() {
    if (isUserLoggedIn()) {
      view.openMainActivity();
    }
  }

  public void loadAccounts() {
    accountList = accounts.getRecentLoggedAccounts();
    showAccounts(accountList);
  }

  public Account getAccountAtIndex(int position) {
    return accountList.get(position);
  }

  public void onAccountClicked(Account selectedAccount) {
    this.selectedAccount = selectedAccount;
    view.showPasswordBox();
  }

  public void loginWithSelectedUser() {
    accounts.login(selectedAccount);
    view.openLoadingActivity();
  }

  public boolean isUserLoggedIn() {
    return accounts.isUserLogged();
  }

  private void showAccounts(List<Account> accounts) {
    view.showAccounts(accounts);
  }

  public interface View {

    void showAccounts(List<Account> accounts);

    void showPasswordBox();

    void openMainActivity();

    void openLoadingActivity();
  }
}
