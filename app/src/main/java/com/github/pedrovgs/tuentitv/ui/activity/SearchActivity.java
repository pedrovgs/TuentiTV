package com.github.pedrovgs.tuentitv.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tuenti.tuentitv.R;

public class SearchActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.search_activity);
  }

  @Override public boolean onSearchRequested() {
    startActivity(new Intent(this, SearchActivity.class));
    return true;
  }
}
