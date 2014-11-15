package com.tuenti.tuentitv.ui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import butterknife.InjectView;
import com.tuenti.tuentitv.R;
import java.util.LinkedList;
import java.util.List;

public class LoginActivity extends BaseActivity {

  @InjectView(R.id.ll_accounts_container) ViewGroup ll_accounts_container;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.login_activity);
    super.onCreate(savedInstanceState);
    hookListeners();
  }

  private void hookListeners() {
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

  @Override protected List getModules() {
    return new LinkedList();
  }

  private class OnFocusChangeAccountListener implements View.OnFocusChangeListener {

    private final View view;

    public OnFocusChangeAccountListener(View view) {
      this.view = view;
    }

    @Override public void onFocusChange(View v, boolean hasFocus) {
      float to = hasFocus ? 1.4f : 1;
      ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", to);
      scaleXAnimator.setDuration(150);
      scaleXAnimator.start();
      ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", to);
      scaleYAnimator.setDuration(150);
      scaleYAnimator.start();
    }
  }
}
