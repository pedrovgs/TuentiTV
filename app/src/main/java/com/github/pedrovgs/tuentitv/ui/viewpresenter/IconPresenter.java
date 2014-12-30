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
