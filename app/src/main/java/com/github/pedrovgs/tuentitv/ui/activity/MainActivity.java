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

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import com.github.pedrovgs.tuentitv.R;
import java.util.LinkedList;
import java.util.List;

/**
 * Activity used to show main application information. This Activity contains one fragment
 * extending from BrowseFragment.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.main_activity);
    super.onCreate(savedInstanceState);
    cancelNotifications();
  }

  private void cancelNotifications() {
    NotificationManager notificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.cancelAll();
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
