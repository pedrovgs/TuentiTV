package com.tuenti.tuentitv.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import com.tuenti.tuentitv.R;
import com.tuenti.tuentitv.ui.presenter.EnterPasswordPresenter;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EnterPasswordActivity extends BaseActivity implements EnterPasswordPresenter.View {

  @Inject EnterPasswordPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.enter_password_activity);
    super.onCreate(savedInstanceState);
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    switch (keyCode) {
      case KeyEvent.KEYCODE_DPAD_DOWN:
        presenter.onDpadDownPressed();
        return true;
      case KeyEvent.KEYCODE_DPAD_LEFT:
        presenter.onDpadLeftPressed();
        return true;
      case KeyEvent.KEYCODE_DPAD_RIGHT:
        presenter.onDpadRightPressed();
        return true;
      case KeyEvent.KEYCODE_DPAD_UP:
        presenter.onDpadUpPressed();
        return true;
      default:
        return super.onKeyDown(keyCode, event);
    }
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
