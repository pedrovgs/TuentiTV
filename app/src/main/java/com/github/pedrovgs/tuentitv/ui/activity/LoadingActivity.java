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
