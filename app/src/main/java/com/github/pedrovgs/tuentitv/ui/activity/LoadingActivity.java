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
package com.github.pedrovgs.tuentitv.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import butterknife.InjectView;
import com.github.pedrovgs.tuentitv.R;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class LoadingActivity extends BaseActivity {

  private static final long LOADING_TIME_IN_MILLIS = 3000;

  @InjectView(R.id.pb_loading) ProgressBar pb_loading;

  private Runnable startMainActivity;
  private Handler handler;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.loading_activity);
    super.onCreate(savedInstanceState);
    pb_loading.getIndeterminateDrawable()
        .setColorFilter(0x32FFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);
    handler = new Handler();
    startMainActivity = new Runnable() {
      @Override public void run() {
        startMainActivity();
      }
    };
    handler.postDelayed(startMainActivity, LOADING_TIME_IN_MILLIS);
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    handler.removeCallbacks(startMainActivity);
  }

  private void startMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
