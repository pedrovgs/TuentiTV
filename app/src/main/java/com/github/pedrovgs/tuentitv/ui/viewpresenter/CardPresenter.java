package com.github.pedrovgs.tuentitv.ui.viewpresenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tuenti.tuentitv.R;
import com.github.pedrovgs.tuentitv.model.CardInfo;

public class CardPresenter extends Presenter {

  private static Context context;
  private static int CARD_WIDTH = 300;
  private static int CARD_HEIGHT = 200;

  static class ViewHolder extends Presenter.ViewHolder {
    private CardInfo cardInfo;
    private ImageCardView imageCardView;
    private Drawable defaultCardImage;
    private PicassoImageCardViewTarget mImageCardViewTarget;

    public ViewHolder(View view) {
      super(view);
      imageCardView = (ImageCardView) view;
      mImageCardViewTarget = new PicassoImageCardViewTarget(imageCardView);
      defaultCardImage = context.getResources().getDrawable(R.drawable.icn_application);
    }

    public void setCardInfo(CardInfo cardInfo) {
      this.cardInfo = cardInfo;
    }

    protected void updateCardViewImage(String url) {
      Picasso.with(context)
          .load(url)
          .resize(CARD_WIDTH, CARD_HEIGHT)
          .centerCrop()
          .placeholder(defaultCardImage)
          .into(mImageCardViewTarget);
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent) {
    context = parent.getContext();

    ImageCardView cardView = new ImageCardView(context);
    cardView.setFocusable(true);
    cardView.setFocusableInTouchMode(true);
    cardView.setBackgroundColor(context.getResources().getColor(R.color.third_color));
    return new ViewHolder(cardView);
  }

  @Override public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    CardInfo cardInfo = (CardInfo) item;
    ((ViewHolder) viewHolder).setCardInfo(cardInfo);
    if (cardInfo.getCardImageUrl() != null) {
      ((ViewHolder) viewHolder).imageCardView.setTitleText(cardInfo.getTitle());
      ((ViewHolder) viewHolder).imageCardView.setContentText(cardInfo.getText());
      ((ViewHolder) viewHolder).imageCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
      ((ViewHolder) viewHolder).updateCardViewImage(cardInfo.getCardImageUrl());
    }
  }

  @Override public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    //Empty
  }

  @Override public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    //Empty
  }

  public static class PicassoImageCardViewTarget implements Target {
    private ImageCardView mImageCardView;

    public PicassoImageCardViewTarget(ImageCardView mImageCardView) {
      this.mImageCardView = mImageCardView;
    }

    @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
      Drawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
      mImageCardView.setMainImage(bitmapDrawable);
    }

    @Override public void onBitmapFailed(Drawable drawable) {
      mImageCardView.setMainImage(drawable);
    }

    @Override public void onPrepareLoad(Drawable drawable) {
      //Empty
    }
  }
}
