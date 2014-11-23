package com.tuenti.tuentitv.ui.presenter;

import com.tuenti.tuentitv.ui.fragment.MainFragment;
import javax.inject.Inject;

/**
 * Class created to work as main view presenter. This presenter has all the responsibility related
 * to the main view presentation logic.
 *
 * Responsibilities:
 *
 * - Obtains a list of favorite contacts.
 *
 * - Obtains a list of recent conversations.
 *
 * - Show a list of all user's contacts sorted alphabetically.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainPresenter {

  private View view;

  @Inject public MainPresenter() {
    //Empty
  }

  public void setView(View view) {
    this.view = view;
  }

  public interface View{

  }
}
