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

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

/**
 * Main class related to login feature.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Accounts {

  private Account loggedAccount;

  @Inject public Accounts() {
    //Empty
  }

  public List<Account> getRecentLoggedAccounts() {
    Account juanma = new Account("Juanma", "https://imrl.tuenti.net/MephbQPFZwIygHTjAA");
    Account emanuela =
        new Account("Emanuela", "https://tuentiimg2-a.akamaihd.net/Meo8zgSyA0mYepIyAA");
    Account luisja =
        new Account("Luis Javier", "https://tuentiimg1-a.akamaihd.net/MeyvDQONIBaVEpFrAA");
    return Arrays.asList(juanma, emanuela, luisja);
  }

  public void login(Account account) {
    loggedAccount = account;
  }

  public boolean isUserLogged() {
    return loggedAccount != null;
  }

  public void logout() {
    loggedAccount = null;
  }
}
