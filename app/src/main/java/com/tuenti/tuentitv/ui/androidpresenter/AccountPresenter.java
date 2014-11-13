package com.tuenti.tuentitv.ui.androidpresenter;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import com.tuenti.tuentitv.R;
import com.tuenti.tuentitv.ui.model.Account;
import com.tuenti.tuentitv.ui.picasso.PicassoImageCardViewTarget;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */

public class AccountPresenter extends Presenter {

  private static final int CARD_WIDTH = 313;
  private static final int CARD_HEIGHT = 176;

  private static Context context;

  static class ViewHolder extends Presenter.ViewHolder {
    private ImageCardView cardView;
    private PicassoImageCardViewTarget imageCardViewTarget;

    public ViewHolder(View view) {
      super(view);
      cardView = (ImageCardView) view;
      imageCardViewTarget = new PicassoImageCardViewTarget(context, cardView);
    }

    protected void updateCardViewImage(String url) {
      Picasso.with(context)
          .load(url)
          .resize(CARD_WIDTH, CARD_HEIGHT)
          .centerCrop()
          .placeholder(R.drawable.ic_launcher)
          .error(R.drawable.ic_launcher)
          .into(imageCardViewTarget);
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent) {
    context = parent.getContext();

    ImageCardView cardView = new ImageCardView(context);
    cardView.setFocusable(true);
    cardView.setFocusableInTouchMode(true);
    cardView.setBackgroundColor(context.getResources().getColor(R.color.white));
    return new ViewHolder(cardView);
  }

  @Override public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    Account account = (Account) item;
    ImageCardView cardView = ((ViewHolder) viewHolder).cardView;
    cardView.setTitleText(account.getName());
    cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
    if (account.getAvatarUrl() != null) {
      ((ViewHolder) viewHolder).updateCardViewImage(account.getAvatarUrl());
    }
  }

  @Override public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    //Empty
  }

  @Override public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    //Empty
  }
}
