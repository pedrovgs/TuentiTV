package com.tuenti.tuentitv.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.tuenti.tuentitv.R;
import com.tuenti.tuentitv.ui.presenter.EnterPasswordPresenter;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EnterPasswordActivity extends BaseActivity implements EnterPasswordPresenter.View {

  public static final String RESULT_KEY = "result";
  public static final int CHANGE_PASSWORD_TIME_IN_MILLIS = 700;

  @Inject EnterPasswordPresenter presenter;
  private boolean isLastPasswordElement;
  private final Handler handler = new Handler();

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.enter_password_activity);
    super.onCreate(savedInstanceState);
    presenter.setView(this);
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

  @Override public void showTopArrowOnCurrentPasswordField() {
    int passwordElementResource = R.drawable.lb_ic_play;
    showPasswordElement(passwordElementResource);
  }

  @Override public void showRightArrowOnCurrentPasswordField() {
    int passwordElementResource = R.drawable.lb_ic_play;
    showPasswordElement(passwordElementResource);
  }

  @Override public void showLeftArrowOnCurrentPasswordField() {
    int passwordElementResource = R.drawable.lb_ic_play;
    showPasswordElement(passwordElementResource);
  }

  @Override public void showDownArrowOnCurrentPasswordField() {
    int passwordElementResource = R.drawable.lb_ic_play;
    showPasswordElement(passwordElementResource);
  }

  @Override public void moveFocusToNextElement() {
    View focusedElement = getCurrentFocus();
    ViewGroup parent = (ViewGroup) focusedElement.getParent().getParent();
    for (int i = 0; i < parent.getChildCount(); i++) {
      ViewGroup child = (ViewGroup) parent.getChildAt(i);
      View ib_element_item = child.findViewById(R.id.ib_password_element);
      if (focusedElement.equals(ib_element_item)) {
        View nextFocusedElement = parent.getChildAt(i + 1);
        nextFocusedElement.requestFocus();
        this.isLastPasswordElement = parent.getChildAt(i + 2) == null;
      }
    }
  }

  @Override public boolean isLastElementFocused() {
    return isLastPasswordElement;
  }

  @Override public void closeViewWithSuccessPassword() {
    finishWithResult();
  }

  @Override public void openLoadingActivity() {
    Intent intent = new Intent(this, LoadingActivity.class);
    startActivity(intent);
  }

  private void finishWithResult() {
    Intent returnIntent = new Intent();
    returnIntent.putExtra(RESULT_KEY, true);
    setResult(RESULT_OK, returnIntent);
    finish();
  }

  private void showPasswordElement(int passwordElementResource) {
    View focusedView = getCurrentFocus();
    ViewGroup parent = (ViewGroup) focusedView.getParent();
    ImageView iv_password_element = (ImageView) parent.findViewById(R.id.iv_password_element);
    iv_password_element.setImageResource(passwordElementResource);
    updatePasswordElementWithAterisk(iv_password_element);
  }

  private void updatePasswordElementWithAterisk(final ImageView iv_password_element) {
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        iv_password_element.setImageResource(R.drawable.lb_ic_pause);
      }
    }, CHANGE_PASSWORD_TIME_IN_MILLIS);
  }
}
