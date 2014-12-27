package com.github.pedrovgs.tuentitv.di;

import android.content.Context;
import com.github.pedrovgs.tuentitv.TuentiTvApplication;
import com.github.pedrovgs.tuentitv.ui.activity.EnterPasswordActivity;
import com.github.pedrovgs.tuentitv.ui.activity.LoadingActivity;
import com.github.pedrovgs.tuentitv.ui.activity.MainActivity;
import com.github.pedrovgs.tuentitv.ui.activity.SearchActivity;
import com.github.pedrovgs.tuentitv.ui.activity.ShowImageActivity;
import com.github.pedrovgs.tuentitv.ui.fragment.MainFragment;
import com.github.pedrovgs.tuentitv.ui.fragment.SearchFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Main Dagger module created to provide every dependency with an application scope. This module is
 * going to be used to include other modules with application scope.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(
    includes = { TuentiTvApplicationModule.class },
    injects = {
        TuentiTvApplication.class, EnterPasswordActivity.class, LoadingActivity.class,
        MainActivity.class, MainFragment.class, SearchActivity.class, SearchFragment.class,
        ShowImageActivity.class
    }, library = true) public class RootModule {

  private final Context context;

  public RootModule(Context context) {
    this.context = context;
  }

  @ApplicationContext @Provides Context provideApplicationContext() {
    return context;
  }
}
