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
package com.github.pedrovgs.tuentitv.presenter;

import javax.inject.Inject;

/**
 * Class created to work as enter password view presenter. This presenter has all the
 * responsibility related to capture user's password and return the result to the previous view.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EnterPasswordPresenter {

  private View view;

  @Inject public EnterPasswordPresenter() {
    // Empty
  }

  public void setView(View view) {
    this.view = view;
  }

  public void onDpadUpPressed() {
    view.showTopArrowOnCurrentPasswordField();
    continueToNextPasswordElement();
  }

  public void onDpadRightPressed() {
    view.showRightArrowOnCurrentPasswordField();
    continueToNextPasswordElement();
  }

  public void onDpadLeftPressed() {
    view.showLeftArrowOnCurrentPasswordField();
    continueToNextPasswordElement();
  }

  public void onDpadDownPressed() {
    view.showDownArrowOnCurrentPasswordField();
    continueToNextPasswordElement();
  }

  private void continueToNextPasswordElement() {
    view.hidePreviousPasswordElements();
    if (view.isLastElementFocused()) {
      view.closeViewWithSuccessPassword();
    } else {
      view.moveFocusToNextElement();
    }
  }

  public interface View {

    void showTopArrowOnCurrentPasswordField();

    void showRightArrowOnCurrentPasswordField();

    void showLeftArrowOnCurrentPasswordField();

    void showDownArrowOnCurrentPasswordField();

    void hidePreviousPasswordElements();

    void moveFocusToNextElement();

    boolean isLastElementFocused();

    void closeViewWithSuccessPassword();
  }
}
