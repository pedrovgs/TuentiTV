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
package com.github.pedrovgs.tuentitv.recommendation.builder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import com.github.pedrovgs.tuentitv.R;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import javax.inject.Inject;

public class RecommendationBuilder {

  private Context context;
  private NotificationManager notificationManager;

  private int id;
  private int priority;
  private int smallIcon;
  private String title;
  private String description;
  private String imageUri;
  private String backgroundUri;
  private PendingIntent pendingIntent;

  @Inject public RecommendationBuilder() {
    //Empty
  }

  public RecommendationBuilder setContext(Context context) {
    this.context = context;
    return this;
  }

  public RecommendationBuilder setId(int id) {
    this.id = id;
    return this;
  }

  public RecommendationBuilder setPriority(int priority) {
    this.priority = priority;
    return this;
  }

  public RecommendationBuilder setTitle(String title) {
    this.title = title;
    return this;
  }

  public RecommendationBuilder setDescription(String description) {
    this.description = description;
    return this;
  }

  public RecommendationBuilder setImage(String uri) {
    imageUri = uri;
    return this;
  }

  public RecommendationBuilder setBackground(String uri) {
    backgroundUri = uri;
    return this;
  }

  public RecommendationBuilder setIntent(PendingIntent intent) {
    pendingIntent = intent;
    return this;
  }

  public RecommendationBuilder setSmallIcon(int resourceId) {
    smallIcon = resourceId;
    return this;
  }

  public Notification build() throws IOException {
    if (notificationManager == null) {
      notificationManager =
          (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    Bundle extras = new Bundle();
    if (backgroundUri != null) {
      extras.putString(Notification.EXTRA_BACKGROUND_IMAGE_URI, backgroundUri);
    }

    Bitmap image = Picasso.with(context).load(imageUri).get();

    Notification notification = new NotificationCompat.BigPictureStyle(
        new NotificationCompat.Builder(context).setContentTitle(title)
            .setContentText(description)
            .setPriority(priority)
            .setLocalOnly(true)
            .setOngoing(true)
            .setColor(context.getResources().getColor(R.color.primary_color))
            .setCategory(Notification.CATEGORY_RECOMMENDATION)
            .setLargeIcon(image)
            .setSmallIcon(smallIcon)
            .setContentIntent(pendingIntent)
            .setExtras(extras)).build();

    notificationManager.notify(id, notification);
    notificationManager = null;
    return notification;
  }
}