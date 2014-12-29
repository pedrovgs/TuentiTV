package com.github.pedrovgs.tuentitv.service;

import android.app.IntentService;
import android.content.Intent;
import com.github.pedrovgs.tuentitv.ui.activity.BaseActivity;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class BaseIntentService extends IntentService {

  /**
   * Creates an IntentService.  Invoked by your subclass's constructor.
   *
   * @param name Used to name the worker thread, important only for debugging.
   */
  public BaseIntentService(String name) {
    super(name);
    injectDependencies();
  }

  @Override protected void onHandleIntent(Intent intent) {
    //Empty
  }

  /**
   * Replace every field annotated using @Inject annotation with the provided dependency specified
   * inside a Dagger module value.
   */
  private void injectDependencies() {
    ((BaseActivity) getApplicationContext()).inject(this);
  }
}
