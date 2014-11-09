package com.tuenti.tuentitv.ui.activity;

import android.os.Bundle;
import com.tuenti.tuentitv.R;
import java.util.LinkedList;
import java.util.List;

public class LoginActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
