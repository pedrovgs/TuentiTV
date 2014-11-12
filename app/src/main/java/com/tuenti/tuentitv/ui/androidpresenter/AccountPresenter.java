package com.tuenti.tuentitv.ui.androidpresenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tuenti.tuentitv.R;
import java.net.URI;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */

public class AccountPresenter extends Presenter {
  private static final String TAG = "CardPresenter";

  private static Context mContext;
  private static int CARD_WIDTH = 313;
  private static int CARD_HEIGHT = 176;

  static class ViewHolder extends Presenter.ViewHolder {
    private Account mAccount;
    private ImageCardView mCardView;
    private Drawable mDefaultCardImage;
    private PicassoImageCardViewTarget mImageCardViewTarget;

    public ViewHolder(View view) {
      super(view);
      mCardView = (ImageCardView) view;
      mImageCardViewTarget = new PicassoImageCardViewTarget(mCardView);
      mDefaultCardImage = mContext.getResources().getDrawable(R.drawable.ic_launcher);
    }

    public void setMovie(Account m) {
      mAccount = m;
    }

    public Account getMovie() {
      return mAccount;
    }

    public ImageCardView getCardView() {
      return mCardView;
    }

    protected void updateCardViewImage(URI uri) {
      Picasso.with(mContext)
          .load(uri.toString())
          .resize(CARD_WIDTH, CARD_HEIGHT)
          .centerCrop()
          .error(mDefaultCardImage);
      //.into(mImageCardViewTarget);
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent) {
    Log.d(TAG, "onCreateViewHolder");
    mContext = parent.getContext();

    ImageCardView cardView = new ImageCardView(mContext);
    cardView.setFocusable(true);
    cardView.setFocusableInTouchMode(true);
    cardView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
    return new ViewHolder(cardView);
  }

  @Override public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    Account account = (Account) item;
    ((ViewHolder) viewHolder).setMovie(account);

    Log.d(TAG, "onBindViewHolder");
    ((ViewHolder) viewHolder).mCardView.setTitleText(account.getTitle());
    ((ViewHolder) viewHolder).mCardView.setContentText(account.getStudio());
    ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
    if (account.getCardImageUrl() != null) {
      ((ViewHolder) viewHolder).updateCardViewImage(account.getCardImageURI());
    }
  }

  @Override public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    Log.d(TAG, "onUnbindViewHolder");
  }

  @Override public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    Log.d(TAG, "onViewAttachedToWindow");
  }

  public static class PicassoImageCardViewTarget implements Target {
    private ImageCardView mImageCardView;

    public PicassoImageCardViewTarget(ImageCardView mImageCardView) {
      this.mImageCardView = mImageCardView;
    }

    @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
      Drawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
      mImageCardView.setMainImage(bitmapDrawable);
    }

    @Override public void onBitmapFailed(Drawable drawable) {
      mImageCardView.setMainImage(drawable);
    }

    @Override public void onPrepareLoad(Drawable drawable) {
      // Do nothing, default_background manager has its own transitions
    }
  }
}
