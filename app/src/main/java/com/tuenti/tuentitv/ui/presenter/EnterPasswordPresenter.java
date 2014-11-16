package com.tuenti.tuentitv.ui.presenter;

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
public class EnterPasswordPresenter {

  private View view;

  @Inject public EnterPasswordPresenter() {
    // Empty
  }

  public void setView(View view) {
    this.view = view;
  }

  public void onDpadUpPressed() {
  }

  public void onDpadRightPressed() {
  }

  public void onDpadLeftPressed() {
  }

  public void onDpadDownPressed() {
  }

  public interface View {


  }
}
