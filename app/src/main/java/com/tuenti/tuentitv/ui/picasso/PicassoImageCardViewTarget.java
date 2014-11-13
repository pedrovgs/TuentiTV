package com.tuenti.tuentitv.ui.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PicassoImageCardViewTarget implements Target {
  private final Context context;
  private final ImageCardView imageCardView;

  public PicassoImageCardViewTarget(Context context, ImageCardView imageCardView) {
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