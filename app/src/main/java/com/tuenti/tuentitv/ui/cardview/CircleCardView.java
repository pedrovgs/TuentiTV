package com.tuenti.tuentitv.ui.cardview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.R.attr;
import android.support.v17.leanback.R.id;
import android.support.v17.leanback.R.layout;
import android.support.v17.leanback.R.styleable;
import android.support.v17.leanback.widget.BaseCardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class CircleCardView extends BaseCardView {

  private static final long FADE_INT_DURATION = 100;

  private ImageView mImageView;
  private View mInfoArea;
  private TextView mTitleView;
  private TextView mContentView;
  private ImageView mBadgeImage;
  private ImageView mBadgeFadeMask;

  public CircleCardView(Context context) {
    this(context, (AttributeSet) null);
  }

  public CircleCardView(Context context, AttributeSet attrs) {
    this(context, attrs, attr.imageCardViewStyle);
  }

  public CircleCardView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    LayoutInflater inflater = LayoutInflater.from(context);
    View v = inflater.inflate(layout.lb_image_card_view, this);
    this.mImageView = (ImageView) v.findViewById(id.main_image);
    this.mImageView.setVisibility(View.INVISIBLE);
    this.mInfoArea = v.findViewById(id.info_field);
    this.mTitleView = (TextView) v.findViewById(id.title_text);
    this.mContentView = (TextView) v.findViewById(id.content_text);
    this.mBadgeImage = (ImageView) v.findViewById(id.extra_badge);
    this.mBadgeFadeMask = (ImageView) v.findViewById(id.fade_mask);
    if (this.mInfoArea != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.lbImageCardView, defStyle, 0);

      try {
        this.setInfoAreaBackground(a.getDrawable(styleable.lbImageCardView_infoAreaBackground));
      } finally {
        a.recycle();
      }
    }
  }

  public final ImageView getMainImageView() {
    return this.mImageView;
  }

  public void setMainImageAdjustViewBounds(boolean adjustViewBounds) {
    if (this.mImageView != null) {
      this.mImageView.setAdjustViewBounds(adjustViewBounds);
    }
  }

  public void setMainImageScaleType(ScaleType scaleType) {
    if (this.mImageView != null) {
      this.mImageView.setScaleType(scaleType);
    }
  }

  public void setMainImage(Drawable drawable) {
    this.setMainImage(drawable, true);
  }

  public void setMainImage(Drawable drawable, boolean fade) {
    if (this.mImageView != null) {
      this.mImageView.setImageDrawable(drawable);
      if (drawable == null) {
        this.mImageView.animate().cancel();
        this.mImageView.setAlpha(1.0F);
        this.mImageView.setVisibility(View.INVISIBLE);
      } else {
        this.mImageView.setVisibility(View.VISIBLE);
        if (fade) {
          this.fadeIn(this.mImageView);
        } else {
          this.mImageView.animate().cancel();
          this.mImageView.setAlpha(1.0F);
        }
      }
    }
  }

  public void setMainImageDimensions(int width, int height) {
    LayoutParams lp = (LayoutParams) this.mImageView.getLayoutParams();
    lp.width = width;
    lp.height = height;
    this.mImageView.setLayoutParams(lp);
  }

  public Drawable getMainImage() {
    return this.mImageView == null ? null : this.mImageView.getDrawable();
  }

  public Drawable getInfoAreaBackground() {
    return this.mInfoArea != null ? this.mInfoArea.getBackground() : null;
  }

  public void setInfoAreaBackground(Drawable drawable) {
    if (this.mInfoArea != null) {
      this.mInfoArea.setBackground(drawable);
      if (this.mBadgeImage != null) {
        this.mBadgeImage.setBackground(drawable);
      }
    }
  }

  public void setInfoAreaBackgroundColor(int color) {
    if (this.mInfoArea != null) {
      this.mInfoArea.setBackgroundColor(color);
      if (this.mBadgeImage != null) {
        this.mBadgeImage.setBackgroundColor(color);
      }
    }
  }

  public void setTitleText(CharSequence text) {
    if (this.mTitleView != null) {
      this.mTitleView.setText(text);
      this.setTextMaxLines();
    }
  }

  public CharSequence getTitleText() {
    return this.mTitleView == null ? null : this.mTitleView.getText();
  }

  public void setContentText(CharSequence text) {
    if (this.mContentView != null) {
      this.mContentView.setText(text);
      this.setTextMaxLines();
    }
  }

  public CharSequence getContentText() {
    return this.mContentView == null ? null : this.mContentView.getText();
  }

  public void setBadgeImage(Drawable drawable) {
    if (this.mBadgeImage != null) {
      if (drawable != null) {
        this.mBadgeImage.setImageDrawable(drawable);
        this.mBadgeImage.setVisibility(View.VISIBLE);
        this.mBadgeFadeMask.setVisibility(View.VISIBLE);
      } else {
        this.mBadgeImage.setVisibility(View.GONE);
        this.mBadgeFadeMask.setVisibility(View.GONE);
      }
    }
  }

  public Drawable getBadgeImage() {
    return this.mBadgeImage == null ? null : this.mBadgeImage.getDrawable();
  }

  private void fadeIn(View v) {
    v.setAlpha(0.0F);
    v.animate().alpha(1.0F).setDuration(FADE_INT_DURATION).start();
  }

  public boolean hasOverlappingRendering() {
    return false;
  }

  private void setTextMaxLines() {
    if (TextUtils.isEmpty(this.getTitleText())) {
      this.mContentView.setMaxLines(2);
    } else {
      this.mContentView.setMaxLines(1);
    }

    if (TextUtils.isEmpty(this.getContentText())) {
      this.mTitleView.setMaxLines(2);
    } else {
      this.mTitleView.setMaxLines(1);
    }
  }

  protected void onDetachedFromWindow() {
    this.mImageView.animate().cancel();
    this.mImageView.setAlpha(1.0F);
    super.onDetachedFromWindow();
  }
}