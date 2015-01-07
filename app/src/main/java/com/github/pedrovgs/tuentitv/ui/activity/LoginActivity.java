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

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnFocusChange;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.model.Account;
import com.github.pedrovgs.tuentitv.presenter.LoginPresenter;
import com.github.pedrovgs.tuentitv.ui.picasso.transformation.CircleTransform;
import com.squareup.picasso.Picasso;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Activity created to show recent logged accounts and perform login process. This activity is
 * going
 * to use LoginPresenter as main collaborator to perform login process.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class LoginActivity extends BaseActivity implements LoginPresenter.View {

  private static final int PASSWORD_REQUEST_CODE = 1;

  @Inject LoginPresenter loginPresenter;

  @InjectView(R.id.ll_accounts_container) ViewGroup ll_accounts_container;
  @InjectView(R.id.iv_app_logo) View iv_app_logo;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.login_activity);
    super.onCreate(savedInstanceState);
    hookListeners();
    initializePresenter();
  }

  @OnFocusChange(R.id.iv_app_logo) void onFocusChanged(boolean focused) {
    if (!focused) {
      iv_app_logo.setFocusable(false);
    }
  }

  @Override public void showAccounts(List<Account> accounts) {
    for (int i = 0; i < accounts.size(); i++) {
      View v_account = ll_accounts_container.getChildAt(i);
      showAccount(accounts.get(i), v_account);
    }
  }

  @Override public void showPasswordBox() {
    Intent intent = new Intent(this, EnterPasswordActivity.class);
    startActivityForResult(intent, PASSWORD_REQUEST_CODE);
  }

  @Override public void openMainActivity() {
    Intent openMainActivityIntent = new Intent(this, MainActivity.class);
    startActivity(openMainActivityIntent);
    finish();
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PASSWORD_REQUEST_CODE && passwordIsCorrect(data)) {
      loginPresenter.loginWithSelectedUser();
    }
  }

  @Override public void closeView() {
    finish();
  }

  private void initializePresenter() {
    loginPresenter.setView(this);
    loginPresenter.initialize();
    loginPresenter.loadAccounts();
  }

  private void hookListeners() {
    hookScaleAnimations();
    hookAccountClickListeners();
  }

  private void hookScaleAnimations() {
    for (int i = 0; i < ll_accounts_container.getChildCount(); i++) {
      View v_account = ll_accounts_container.getChildAt(i);
      configureScaleAnimator(v_account);
    }
  }

  private void configureScaleAnimator(View v_account_container) {
    View ib_account = v_account_container.findViewById(R.id.ib_account);
    View tv_account_name = v_account_container.findViewById(R.id.tv_account_name);
    ib_account.setOnFocusChangeListener(new OnFocusChangeAccountListener(tv_account_name));
  }

  private void showAccount(Account account, View v_account_container) {
    TextView tv_account_name = (TextView) v_account_container.findViewById(R.id.tv_account_name);
    ImageButton ib_account = (ImageButton) v_account_container.findViewById(R.id.ib_account);
    tv_account_name.setText(account.getName());
    Picasso.with(this)
        .load(account.getAvatarUrl())
        .transform(new CircleTransform(getResources().getDimension(R.dimen.account_item_size)))
        .placeholder(R.drawable.icn_user_default_blue)
        .into(ib_account);
  }

  private void hookAccountClickListeners() {
    for (int i = 0; i < ll_accounts_container.getChildCount() - 1; i++) {
      ImageButton ib_accoun =
          (ImageButton) ll_accounts_container.getChildAt(i).findViewById(R.id.ib_account);
      ib_accoun.setOnClickListener(new OnAccountClickListener(i));
    }
  }

  private boolean passwordIsCorrect(Intent data) {
    return data != null && data.getBooleanExtra(EnterPasswordActivity.RESULT_KEY, false);
  }

  private class OnFocusChangeAccountListener implements View.OnFocusChangeListener {

    private final View view;

    public OnFocusChangeAccountListener(View view) {
      this.view = view;
    }

    @Override public void onFocusChange(View v, boolean hasFocus) {
      float to = hasFocus ? 1.6f : 1;
      performScaleXAnimation(to);
      performScaleYAnimation(to);
      performAlphaAnimation(hasFocus);
    }

    private void performScaleXAnimation(float to) {
      ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", to);
      scaleXAnimator.setDuration(getResources().getInteger(R.integer.short_animation_time));
      scaleXAnimator.start();
    }

    private void performScaleYAnimation(float to) {
      ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", to);
      scaleYAnimator.setDuration(getResources().getInteger(R.integer.short_animation_time));
      scaleYAnimator.start();
    }

    private void performAlphaAnimation(boolean hasFocus) {
      float toAlpha = hasFocus ? 1f : 0.5f;
      ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", toAlpha);
      alphaAnimator.setDuration(getResources().getInteger(R.integer.short_animation_time));
      alphaAnimator.start();
    }
  }

  private class OnAccountClickListener implements View.OnClickListener {

    private final int position;

    public OnAccountClickListener(int position) {
      this.position = position;
    }

    @Override public void onClick(View v) {
      Account selectedAccount = loginPresenter.getAccountAtIndex(position);
      loginPresenter.onAccountClicked(selectedAccount);
    }
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
