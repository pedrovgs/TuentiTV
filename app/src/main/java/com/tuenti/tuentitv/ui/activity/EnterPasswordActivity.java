package com.tuenti.tuentitv.ui.activity;

import android.os.Bundle;
import com.tuenti.tuentitv.R;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EnterPasswordActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.enter_password_activity);
    super.onCreate(savedInstanceState);
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
