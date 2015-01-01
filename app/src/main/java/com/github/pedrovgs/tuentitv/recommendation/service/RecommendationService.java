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

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.model.Chat;
import com.github.pedrovgs.tuentitv.model.ConversationSummary;
import com.github.pedrovgs.tuentitv.recommendation.builder.RecommendationBuilder;
import com.github.pedrovgs.tuentitv.ui.activity.LoginActivity;
import java.io.IOException;
import javax.inject.Inject;

/**
 * IntentService extension created to show random recommendations. This class is not really needed,
 * you can show recommendations using RecommendationBuilder or just NotificationManager from any
 * part of your Android application. This service is used to show you can use also a service in
 * Android TV applications.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class RecommendationService extends BaseIntentService {

  private static final String SERVICE_NAME = "RecommendationService";

  @Inject Chat chat;
  @Inject RecommendationBuilder recommendationBuilder;

  public RecommendationService() {
    super(SERVICE_NAME);
  }

  @Override protected void onHandleIntent(Intent intent) {
    new Thread(new Runnable() {
      @Override public void run() {
        ConversationSummary conversation = getRandomRecommendation();
        configureRecommendationBuilder();
        prepareRecommendation(conversation);
        showRecommendation();
      }
    });
  }

  private ConversationSummary getRandomRecommendation() {
    return chat.getLastUnreadConversation();
  }

  private void showRecommendation() {
    try {
      recommendationBuilder.build();
    } catch (IOException e) {
      Log.e(SERVICE_NAME, "IOException building recommendation.", e);
    }
  }

  private void prepareRecommendation(ConversationSummary conversation) {
    recommendationBuilder.setBackground(conversation.getCardImageUrl())
        .setId(conversation.getId().hashCode())
        .setPriority(Integer.MAX_VALUE)
        .setTitle(conversation.getTitle())
        .setDescription(conversation.getLastMessage())
        .setImage(conversation.getCardImageUrl())
        .setIntent(getPendingIntent());
  }

  private void configureRecommendationBuilder() {
    recommendationBuilder.setContext(getApplicationContext()).setSmallIcon(R.drawable.icn_wink);
  }

  private PendingIntent getPendingIntent() {
    Intent detailsIntent = new Intent(this, LoginActivity.class);
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    stackBuilder.addParentStack(LoginActivity.class);
    stackBuilder.addNextIntent(detailsIntent);
    PendingIntent intent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    return intent;
  }
}
