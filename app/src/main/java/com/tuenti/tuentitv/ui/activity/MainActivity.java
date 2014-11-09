package com.tuenti.tuentitv.ui.activity;

import android.os.Bundle;
import com.tuenti.tuentitv.R;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
