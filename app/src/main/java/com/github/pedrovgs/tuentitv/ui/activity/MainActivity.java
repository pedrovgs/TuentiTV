package com.github.pedrovgs.tuentitv.ui.activity;

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
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
