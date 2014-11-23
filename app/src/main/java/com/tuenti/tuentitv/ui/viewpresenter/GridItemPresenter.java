package com.tuenti.tuentitv.ui.viewpresenter;

import android.graphics.Color;
import android.support.v17.leanback.widget.Presenter;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tuenti.tuentitv.R;

public class GridItemPresenter extends Presenter {

  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    TextView view = new TextView(parent.getContext());
    view.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
    view.setFocusable(true);
    view.setFocusableInTouchMode(true);
    view.setBackgroundColor(parent.getResources().getColor(R.color.primary_color));
    view.setTextColor(Color.WHITE);
    view.setGravity(Gravity.CENTER);
    return new ViewHolder(view);
  }

  public void onBindViewHolder(ViewHolder viewHolder, Object item) {
    ((TextView) viewHolder.view).setText((String) item);
  }

  public void onUnbindViewHolder(ViewHolder viewHolder) {
  }
}
