package com.github.pedrovgs.tuentitv.ui.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.github.pedrovgs.tuentitv.ui.cardview.CircleCardView;

public class PicassoImageCardViewTarget implements Target {
  private final Context context;
  private final CircleCardView imageCardView;

  public PicassoImageCardViewTarget(Context context, CircleCardView imageCardView) {
    this.context = context;
    this.imageCardView = imageCardView;
  }

  @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
    Drawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
    imageCardView.setMainImage(bitmapDrawable);
  }

  @Override public void onBitmapFailed(Drawable drawable) {
    imageCardView.setMainImage(drawable);
  }

  @Override public void onPrepareLoad(Drawable drawable) {
    //Empty
  }
}