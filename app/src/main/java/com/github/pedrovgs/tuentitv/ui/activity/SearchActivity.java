package com.github.pedrovgs.tuentitv.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.tuenti.tuentitv.R;
import java.util.LinkedList;
import java.util.List;

public class SearchActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.search_activity);
  }

  @Override public boolean onSearchRequested() {
    startActivity(new Intent(this, SearchActivity.class));
    return true;
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
