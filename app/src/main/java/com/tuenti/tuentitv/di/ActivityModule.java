package com.tuenti.tuentitv.di;

import android.content.Context;
import com.tuenti.tuentitv.ui.activity.LoginActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Main Dagger module created to provide every dependency with an application scope. This module is
 * going to be used to include other modules with application scope.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(injects = LoginActivity.class, library = true) public class ActivityModule {

  private final Context context;

  public ActivityModule(Context context) {
    this.context = context;
  }

  @ActivityContext @Provides Context provideApplicationContext() {
    return context;
  }
}
