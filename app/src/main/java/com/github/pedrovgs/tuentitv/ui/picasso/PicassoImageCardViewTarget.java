package com.github.pedrovgs.tuentitv.ui.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Picasso Target implementation used to load images using Picasso into ImageCard views.
 */
public class PicassoImageCardViewTarget implements Target {

  private ImageCardView imageCardView;

  public PicassoImageCardViewTarget(ImageCardView mImageCardView) {
    this.imageCardView = mImageCardView;
  }

  @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
    Drawable bitmapDrawable = new BitmapDrawable(imageCardView.getContext().getResources(), bitmap);
    imageCardView.setMainImage(bitmapDrawable);
  }

  @Override public void onBitmapFailed(Drawable drawable) {
    imageCardView.setMainImage(drawable);
  }

  @Override public void onPrepareLoad(Drawable drawable) {
    //Empty
  }
}
