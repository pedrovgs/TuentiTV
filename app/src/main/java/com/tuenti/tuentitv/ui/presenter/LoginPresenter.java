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
public class LoginPresenter {

  private View view;
  private List<Account> accounts;
  private Account selectedAccount;

  @Inject public LoginPresenter() {
    // Empty
  }

  public void setView(View view) {
    this.view = view;
  }

  public void loadAccounts() {
    Account juanma = new Account("Juanma", "https://imrl.tuenti.net/MephbQPFZwIygHTjAA");
    Account emanuela = new Account("Emanuela", "https://imrl.tuenti.net/MepkRwS-zo7fxDoPAA");
    Account luisja =
        new Account("Luis Javier", "https://tuentiimg1-a.akamaihd.net/MeyvDQONIBaVEpFrAA");
    accounts = Arrays.asList(juanma, emanuela, luisja);
    showAccounts(accounts);
  }

  public Account getAccountAtIndex(int position) {
    return accounts.get(position);
  }

  private void showAccounts(List<Account> accounts) {
    view.showAccounts(accounts);
  }

  public void onAccountClicked(Account selectedAccount) {
    this.selectedAccount = selectedAccount;
    view.showPasswordBox();
  }

  public interface View {

    void showAccounts(List<Account> accounts);

    void showPasswordBox();
  }
}
