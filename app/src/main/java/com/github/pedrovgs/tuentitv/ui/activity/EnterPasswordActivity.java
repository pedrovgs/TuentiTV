/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.tuentitv.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.presenter.EnterPasswordPresenter;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Activity used to show enter password user interface and handle all the presentation logic
 * related to the enter password process.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EnterPasswordActivity extends BaseActivity implements EnterPasswordPresenter.View {

  public static final String RESULT_KEY = "result";
  public static final int CHANGE_PASSWORD_TIME_IN_MILLIS = 700;
  private static final long CLOSE_DELAY = 1000;

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
    int passwordElementResource = R.drawable.icn_arrow_top;
    showPasswordElement(passwordElementResource);
  }

  @Override public void showRightArrowOnCurrentPasswordField() {
    int passwordElementResource = R.drawable.icn_arrow_right;
    showPasswordElement(passwordElementResource);
  }

  @Override public void showLeftArrowOnCurrentPasswordField() {
    int passwordElementResource = R.drawable.icn_arrow_left;
    showPasswordElement(passwordElementResource);
  }

  @Override public void showDownArrowOnCurrentPasswordField() {
    int passwordElementResource = R.drawable.icn_arrow_down;
    showPasswordElement(passwordElementResource);
  }

  @Override public void hidePreviousPasswordElements() {
    View currentViewWithFocus = getCurrentFocus();
    ViewGroup parent = (ViewGroup) currentViewWithFocus.getParent();
    for (int i = 0; i < parent.getChildCount(); i++) {
      View ib_element_item = parent.getChildAt(i);
      if (currentViewWithFocus.equals(ib_element_item)) {
        ImageButton previousFocusedElement = (ImageButton) parent.getChildAt(i - 1);
        updatePasswordElementWithAsterisk(previousFocusedElement, 0);
      }
    }
  }

  @Override public void moveFocusToNextElement() {
    View focusedElement = getCurrentFocus();
    ViewGroup parent = (ViewGroup) focusedElement.getParent();
    for (int i = 0; i < parent.getChildCount(); i++) {
      View ib_element_item = parent.getChildAt(i);
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
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        finishWithResult();
      }
    }, CLOSE_DELAY);
  }

  private void finishWithResult() {
    Intent returnIntent = new Intent();
    returnIntent.putExtra(RESULT_KEY, true);
    setResult(RESULT_OK, returnIntent);
    finish();
  }

  private void showPasswordElement(int passwordElementResource) {
    ImageButton ib_password_element = (ImageButton) getCurrentFocus();
    ib_password_element.setImageResource(passwordElementResource);
    ib_password_element.setBackgroundResource(R.color.transparent);
    LinearLayout.LayoutParams lp =
        (LinearLayout.LayoutParams) ib_password_element.getLayoutParams();
    lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
    ib_password_element.setLayoutParams(lp);
    updatePasswordElementWithAsterisk(ib_password_element, CHANGE_PASSWORD_TIME_IN_MILLIS);
  }

  private void updatePasswordElementWithAsterisk(final ImageButton ib_password_element,
      long delay) {
    if (ib_password_element == null) {
      return;
    }
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        ib_password_element.setImageResource(R.drawable.icn_pass_ok_blue);
      }
    }, delay);
  }
}
