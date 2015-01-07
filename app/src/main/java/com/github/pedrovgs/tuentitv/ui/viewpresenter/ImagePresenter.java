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
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.ui.data.ImageInfo;
import com.github.pedrovgs.tuentitv.ui.util.Util;
import com.squareup.picasso.Picasso;

/**
 * Android Presenter extension created to show ImageInfo information using an ImageView. This
 * presenter is used to show MediaElements obtained from MediaGallery.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class ImagePresenter extends Presenter {

  private static final int IMAGE_WIDTH = 170;
  private static final int IMAGE_HEIGHT = 115;
  private static Context context;

  static class ViewHolder extends Presenter.ViewHolder {

    private ImageView imageView;

    public ViewHolder(View view) {
      super(view);
      imageView = (ImageView) view.findViewById(R.id.iv_media_element);
    }

    protected void updateCardViewImage(String url) {
      Picasso.with(context)
          .load(url)
          .resize(Util.convertDpToPixel(context, IMAGE_HEIGHT),
              Util.convertDpToPixel(context, IMAGE_WIDTH))
          .centerCrop()
          .placeholder(R.color.third_color)
          .into(imageView);
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent) {
    context = parent.getContext();
    View view = LayoutInflater.from(context).inflate(R.layout.image_item, null);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    ImageInfo imageInfo = (ImageInfo) item;
    if (imageInfo.getImageUrl() != null) {
      ((ViewHolder) viewHolder).updateCardViewImage(imageInfo.getImageUrl());
    }
  }

  @Override public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    //Empty
  }

  @Override public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    //Empty
  }
}
