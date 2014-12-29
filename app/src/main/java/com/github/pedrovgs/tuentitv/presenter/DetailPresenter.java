package com.github.pedrovgs.tuentitv.presenter;

import android.util.Log;
import com.github.pedrovgs.tuentitv.model.Agenda;
import com.github.pedrovgs.tuentitv.model.Chat;
import com.github.pedrovgs.tuentitv.ui.data.CardInfo;
import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DetailPresenter {

  private final Agenda agenda;
  private final Chat chat;

  private View view;

  @Inject public DetailPresenter(Agenda agenda, Chat chat) {
    this.agenda = agenda;
    this.chat = chat;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void loadContent(String contentId) {
    CardInfo cardInfo = loadCardInfo(contentId);
    Log.e("DEPURAR", "CONTENTID = " + contentId);
  }

  private CardInfo loadCardInfo(String contentId) {
    CardInfo cardInfo = null;
    cardInfo = agenda.getContactById(contentId);
    if (cardInfo == null) {
      cardInfo = chat.getConversationById(contentId);
    }
    return cardInfo;
  }

  public interface View {

  }
}
