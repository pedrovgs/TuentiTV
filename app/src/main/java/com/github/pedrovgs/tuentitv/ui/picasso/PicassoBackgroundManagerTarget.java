package com.github.pedrovgs.tuentitv.ui.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.app.BackgroundManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Picasso target implementation for updating background images inside leanback fragments.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PicassoBackgroundManagerTarget implements Target {
  BackgroundManager backgroundManager;

  public PicassoBackgroundManagerTarget(BackgroundManager backgroundManager) {
    this.backgroundManager = backgroundManager;
  }

  @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
    this.backgroundManager.setBitmap(bitmap);
  }

  @Override public void onBitmapFailed(Drawable drawable) {
    this.backgroundManager.setDrawable(drawable);
  }

  @Override public void onPrepareLoad(Drawable drawable) {
    this.backgroundManager.setDrawable(drawable);
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PicassoBackgroundManagerTarget that = (PicassoBackgroundManagerTarget) o;

    if (!backgroundManager.equals(that.backgroundManager)) {
      return false;
    }

    return true;
  }

  @Override public int hashCode() {
    return backgroundManager.hashCode();
  }
}
