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
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.ui.data.CardInfo;
import com.github.pedrovgs.tuentitv.ui.picasso.PicassoImageCardViewTarget;
import com.github.pedrovgs.tuentitv.ui.util.Util;
import com.squareup.picasso.Picasso;

/**
 * Android Presenter extension created to show CardInfo information using an ImageCardView.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class CardPresenter extends Presenter {

  private static Context context;
  private static int CARD_WIDTH = 170;
  private static int CARD_HEIGHT = 115;

  static class ViewHolder extends Presenter.ViewHolder {

    private ImageCardView imageCardView;
    private Drawable defaultCardImage;
    private PicassoImageCardViewTarget imageCardViewTarget;

    public ViewHolder(View view) {
      super(view);
      imageCardView = (ImageCardView) view;
      imageCardViewTarget = new PicassoImageCardViewTarget(imageCardView);
      defaultCardImage = context.getResources().getDrawable(R.drawable.icn_application);
    }

    protected void updateCardViewImage(String url) {
      Picasso.with(context)
          .load(url)
          .resize(Util.convertDpToPixel(context, CARD_WIDTH),
              Util.convertDpToPixel(context, CARD_HEIGHT))
          .centerCrop()
          .placeholder(defaultCardImage)
          .into(imageCardViewTarget);
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent) {
    context = parent.getContext();

    ImageCardView cardView = new ImageCardView(context);
    cardView.setFocusable(true);
    cardView.setBackgroundColor(context.getResources().getColor(R.color.third_color));
    return new ViewHolder(cardView);
  }

  @Override public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    CardInfo cardInfo = (CardInfo) item;
    if (cardInfo.getCardImageUrl() != null) {
      ((ViewHolder) viewHolder).imageCardView.setTitleText(cardInfo.getTitle());
      ((ViewHolder) viewHolder).imageCardView.setContentText(cardInfo.getText());
      int cardWidth = Util.convertDpToPixel(context, CARD_WIDTH);
      int cardHeight = Util.convertDpToPixel(context, CARD_HEIGHT);
      ((ViewHolder) viewHolder).imageCardView.setMainImageDimensions(cardWidth, cardHeight);
      ((ViewHolder) viewHolder).updateCardViewImage(cardInfo.getCardImageUrl());
    }
  }

  @Override public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    //Empty
  }

  @Override public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    //Empty
  }
}
