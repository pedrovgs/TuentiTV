package com.tuenti.tuentitv.ui.presenter;

import com.tuenti.tuentitv.ui.model.Account;
import java.util.Arrays;
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
public class LoginViewPresenter {

  private View view;

  @Inject public LoginViewPresenter() {
    // Empty
  }

  public void setView(View view) {
    this.view = view;
  }

  public void loadAccounts() {
    Account juanma = new Account("Juanma", "https://tuentiimg1-a.akamaihd.net/MeWkoQRanJKRgeYFAA");
    Account emanuela =
        new Account("Emanuela", "https://tuentiimg2-a.akamaihd.net/Meo8zgSyA0mYepIyAA");
    Account luisja =
        new Account("Luis Javier", "https://tuentiimg1-a.akamaihd.net/MeyvDQONIBaVEpFrAA");
    Account pedro =
        new Account("Pedro Vicente", "https://tuentiimg0-a.akamaihd.net/MeVNOAO1hS95RMX9AA");
    Account addAccount = new Account("Add account", null);
    List<Account> accounts = Arrays.asList(juanma, emanuela, luisja, pedro, addAccount);
    showAccounts(accounts);
  }

  private void showAccounts(List<Account> accounts) {
    view.showAccounts(accounts);
  }

  public interface View {

    void showAccounts(List<Account> accounts);
  }
}
