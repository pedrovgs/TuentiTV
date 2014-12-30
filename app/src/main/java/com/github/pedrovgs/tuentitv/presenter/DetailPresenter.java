/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.tuentitv.presenter;

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
    showBackground(cardInfo);
    showCardInfoDetails(cardInfo);
  }

  private void showCardInfoDetails(CardInfo cardInfo) {
    view.showCardInfo(cardInfo);
  }

  private void showBackground(CardInfo cardInfo) {
    view.showBackground(cardInfo.getSecondaryImage());
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

    void showBackground(String backgroundUrl);

    void showCardInfo(CardInfo cardInfo);
  }
}
