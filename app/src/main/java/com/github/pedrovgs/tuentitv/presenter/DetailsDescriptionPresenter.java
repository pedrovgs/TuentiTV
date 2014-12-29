package com.github.pedrovgs.tuentitv.presenter;

import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;
import com.github.pedrovgs.tuentitv.ui.data.CardInfo;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
  @Override protected void onBindDescription(ViewHolder viewHolder, Object item) {
    CardInfo cardInfo = (CardInfo) item;

    if (cardInfo != null) {
      viewHolder.getTitle().setText(cardInfo.getTitle());
      viewHolder.getSubtitle().setText(cardInfo.getText());
    }
  }
}
