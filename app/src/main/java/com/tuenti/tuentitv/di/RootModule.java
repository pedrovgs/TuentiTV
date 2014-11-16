package com.tuenti.tuentitv.di;

import android.content.Context;
import com.tuenti.tuentitv.TuentiTvApplication;
import com.tuenti.tuentitv.ui.activity.EnterPasswordActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Main Dagger module created to provide every dependency with an application scope. This module is
 * going to be used to include other modules with application scope.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(injects = { TuentiTvApplication.class, EnterPasswordActivity.class }, library = true)
public class RootModule {

  private final Context context;

  public RootModule(Context context) {
    this.context = context;
  }

  @ApplicationContext @Provides Context provideApplicationContext() {
    return context;
  }
}
