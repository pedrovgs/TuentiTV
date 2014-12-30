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
package com.github.pedrovgs.tuentitv.recommendation.service;

import android.app.IntentService;
import android.content.Intent;
import com.github.pedrovgs.tuentitv.TuentiTvApplication;

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
  }

  @Override public void onCreate() {
    super.onCreate();
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
    ((TuentiTvApplication) getApplication()).inject(this);
  }
}
