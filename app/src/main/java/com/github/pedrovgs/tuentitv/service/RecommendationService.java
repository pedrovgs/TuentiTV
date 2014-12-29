package com.github.pedrovgs.tuentitv.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import com.github.pedrovgs.tuentitv.ui.activity.LoginActivity;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class RecommendationService extends IntentService {

  private static final String SERVICE_NAME = "RecommendationService";

  public RecommendationService() {
    super(SERVICE_NAME);
  }

  @Override protected void onHandleIntent(Intent intent) {
    
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
