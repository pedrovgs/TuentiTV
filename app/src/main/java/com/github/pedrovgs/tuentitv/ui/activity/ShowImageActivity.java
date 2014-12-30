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

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.InjectView;
import com.github.pedrovgs.tuentitv.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.LinkedList;
import java.util.List;

public class ShowImageActivity extends BaseActivity {

  public static final String IMAGE_URL_EXTRA = "image_url_extra";

  private static final int ALPHA_ANIMATION_DURATION = 1000;

  @InjectView(R.id.iv_media_element) ImageView iv_media_element;
  @InjectView(R.id.pb_loading) ProgressBar pb_loading;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.show_image_activity);
    super.onCreate(savedInstanceState);
    String imageUrl = getImageUrlFromExtras();
    Picasso.with(this).load(imageUrl).into(iv_media_element, new Callback() {
      @Override public void onSuccess() {
        animateImageView();
        updateImageViewBackground();
        hideLoading();
      }

      @Override public void onError() {
        finish();
      }
    });
  }

  private void animateImageView() {
    AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
    alphaAnimation.setDuration(ALPHA_ANIMATION_DURATION);
    alphaAnimation.setFillAfter(true);
    iv_media_element.startAnimation(alphaAnimation);
    iv_media_element.setVisibility(View.VISIBLE);
  }

  private void updateImageViewBackground() {
    int backgroundColor = getResources().getColor(R.color.third_color);
    iv_media_element.setBackgroundColor(backgroundColor);
  }

  private void hideLoading() {
    pb_loading.setVisibility(View.GONE);
  }

  @Override protected List getModules() {
    return new LinkedList();
  }

  public String getImageUrlFromExtras() {
    return getIntent().getExtras().getString(IMAGE_URL_EXTRA);
  }
}
