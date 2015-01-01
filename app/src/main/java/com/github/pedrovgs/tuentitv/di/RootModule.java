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
package com.github.pedrovgs.tuentitv.di;

import android.content.Context;
import com.github.pedrovgs.tuentitv.TuentiTvApplication;
import com.github.pedrovgs.tuentitv.recommendation.service.RecommendationService;
import com.github.pedrovgs.tuentitv.ui.activity.DetailActivity;
import com.github.pedrovgs.tuentitv.ui.activity.EnterPasswordActivity;
import com.github.pedrovgs.tuentitv.ui.activity.LoadingActivity;
import com.github.pedrovgs.tuentitv.ui.activity.MainActivity;
import com.github.pedrovgs.tuentitv.ui.activity.SearchActivity;
import com.github.pedrovgs.tuentitv.ui.activity.ShowImageActivity;
import com.github.pedrovgs.tuentitv.ui.fragment.DetailFragment;
import com.github.pedrovgs.tuentitv.ui.fragment.MainFragment;
import com.github.pedrovgs.tuentitv.ui.fragment.SearchFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Main Dagger module created to provide every dependency with an application scope and Application
 * context. This module is  going to be used to include other modules with application scope and
 * provide some Android common dependencies.
 *
 * This module injects every Activity/Fragment of this project because we are going to use activity
 * graphs just to support a Navigator implementation described in this talk:
 * http://www.slideshare.net/PedroVicenteGmezSnch/effective-android-ui-english
 *
 * Presenter implementations are injected over the activity graph without any provisioning method
 * and that's why we don't need to inject Activities and Fragments in activity scope modules.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(
    includes = { TuentiTvApplicationModule.class },
    injects = {
        TuentiTvApplication.class, EnterPasswordActivity.class, LoadingActivity.class,
        MainActivity.class, MainFragment.class, SearchActivity.class, SearchFragment.class,
        ShowImageActivity.class, DetailActivity.class, DetailFragment.class,
        RecommendationService.class
    }, library = true) public class RootModule {

  private final Context context;

  public RootModule(Context context) {
    this.context = context;
  }

  @ApplicationContext @Provides Context provideApplicationContext() {
    return context;
  }
}
