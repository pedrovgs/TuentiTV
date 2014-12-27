package com.github.pedrovgs.tuentitv.ui.viewpresenter;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.ui.data.IconInfo;

public class IconPresenter extends Presenter {

  private IconInfo iconInfo;
  private ImageView iv_icon;
  private TextView tv_icon_title;

  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    Context context = parent.getContext();
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = layoutInflater.inflate(R.layout.icon_item, null);
    iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
    tv_icon_title = (TextView) view.findViewById(R.id.tv_icon_title);
    return new ViewHolder(view);
  }

  public void onBindViewHolder(ViewHolder viewHolder, Object item) {
    iconInfo = (IconInfo) item;
    tv_icon_title.setText(iconInfo.getTitle());
    iv_icon.setImageResource(iconInfo.getIconId());
  }

  public void onUnbindViewHolder(ViewHolder viewHolder) {
    //Empty
  }
}
