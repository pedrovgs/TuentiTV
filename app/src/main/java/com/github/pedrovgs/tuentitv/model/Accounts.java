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
package com.github.pedrovgs.tuentitv.model;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Main class related to accounts feature. This class contains the responsibility to return recent
 * logged accounts, check if the user is logged and perform login, logout operations. All the data
 * this class returns is mocked for this sample.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Accounts {

  private final List<Account> recentLoggedAccounts;
  private Account loggedAccount;

  @Inject public Accounts() {
    this.recentLoggedAccounts = new LinkedList<Account>();
    loadRecentLoggedAccounts();
  }

  /**
   * @return last three recent logged accounts sorted by last login date.
   */
  public List<Account> getRecentLoggedAccounts() {
    return recentLoggedAccounts;
  }

  /**
   * Perform login using an Account object to be used as logged Account during the application
   * lifecycle.
   */
  public void login(Account account) {
    loggedAccount = account;
  }

  /**
   * @return true if there is any user logged in this application and false if not.
   */
  public boolean isUserLogged() {
    return loggedAccount != null;
  }

  /**
   * Perform logout process and remove the previous logged account as logged account.
   */
  public void logout() {
    loggedAccount = null;
  }

  private void loadRecentLoggedAccounts() {
    recentLoggedAccounts.add(new Account("Juanma", "https://imrl.tuenti.net/MephbQPFZwIygHTjAA"));
    recentLoggedAccounts.add(
        new Account("Emanuela", "https://tuentiimg2-a.akamaihd.net/Meo8zgSyA0mYepIyAA"));
    recentLoggedAccounts.add(
        new Account("Luis Javier", "https://tuentiimg1-a.akamaihd.net/MeyvDQONIBaVEpFrAA"));
  }
}
