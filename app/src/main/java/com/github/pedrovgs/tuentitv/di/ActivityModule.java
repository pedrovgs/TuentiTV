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
import com.github.pedrovgs.tuentitv.ui.activity.LoginActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Main Dagger module created to provide every dependency with an application scope. This module is
 * used to create every Activity ObjectGraph and add the Activity context to the graph associated
 * with the named annotation @ActivityContext.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(injects = { LoginActivity.class }, library = true) public class ActivityModule {

  private final Context context;

  public ActivityModule(Context context) {
    this.context = context;
  }

  @ActivityContext @Provides Context provideApplicationContext() {
    return context;
  }
}
