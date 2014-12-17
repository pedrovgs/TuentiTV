package com.tuenti.tuentitv.model;

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
}
