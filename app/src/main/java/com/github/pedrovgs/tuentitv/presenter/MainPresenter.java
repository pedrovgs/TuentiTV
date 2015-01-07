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

import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.model.Accounts;
import com.github.pedrovgs.tuentitv.model.Agenda;
import com.github.pedrovgs.tuentitv.model.Chat;
import com.github.pedrovgs.tuentitv.model.Contact;
import com.github.pedrovgs.tuentitv.model.ConversationSummary;
import com.github.pedrovgs.tuentitv.model.MediaElement;
import com.github.pedrovgs.tuentitv.model.MediaGallery;
import com.github.pedrovgs.tuentitv.ui.data.CardInfo;
import com.github.pedrovgs.tuentitv.ui.data.IconInfo;
import com.github.pedrovgs.tuentitv.ui.data.ImageInfo;
import com.github.pedrovgs.tuentitv.ui.navigator.Navigator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Class created to work as main view presenter. This presenter has all the responsibility related
 * to the main view presentation logic: obtain a list of favorite contacts, obtain a list of recent
 * conversations, show a list of all user's contacts sorted alphabetically.
 *
 * Main collaborators of this class ar Accounts, Agenda, MediaGallery and Chat. Collaborators
 * needed
 * to perform all the business/presentation logic related to this view.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainPresenter {

  private final Accounts accounts;
  private final Agenda agenda;
  private final MediaGallery mediaGallery;
  private final Chat chat;
  private final Navigator navigator;

  private View view;

  @Inject
  public MainPresenter(Accounts accounts, Agenda agenda, MediaGallery mediaGallery, Chat chat,
      Navigator navigator) {
    this.accounts = accounts;
    this.agenda = agenda;
    this.mediaGallery = mediaGallery;
    this.chat = chat;
    this.navigator = navigator;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void loadData() {
    view.showDefaultBackground();
    List<CardInfo> favorites = getFavoriteContacts();
    List<CardInfo> conversations = getConversations();
    List<CardInfo> contacts = getAllContacts();
    List<ImageInfo> mediaElements = getAllMediaElements();
    List<IconInfo> preferences = getPreferences();
    view.showMainInformation(favorites, conversations, contacts, mediaElements, preferences);
  }

  public void onCardInfoSelected(CardInfo cardInfo) {
    if (cardInfo != null) {
      view.updateBackground(cardInfo.getSecondaryImage());
    }
  }

  public void onCardInfoClicked(CardInfo item) {
    view.cancelPendingBackgroundUpdates();
    navigator.openDetailView(item.getId());
  }

  public void onImageInfoSelected(ImageInfo imageInfo) {
    if (imageInfo != null) {
      view.updateBackground(imageInfo.getImageUrl());
    }
  }

  public void onImageInfoClicked(ImageInfo item) {
    navigator.openImageView(item.getImageUrl());
  }

  public void onPreferencesSelected() {
    view.showDefaultBackground();
  }

  public void onSearchIconClicked() {
    navigator.openSearchView();
  }

  public void logout() {
    accounts.logout();
    navigator.openLoginView();
    view.closeView();
  }

  private List<CardInfo> getFavoriteContacts() {
    List<Contact> favorites = agenda.getFavorites();
    return new LinkedList<CardInfo>(favorites);
  }

  private List<CardInfo> getConversations() {
    List<ConversationSummary> conversations = chat.getRecentConversations();
    return new ArrayList<CardInfo>(conversations);
  }

  private List<CardInfo> getAllContacts() {
    List<Contact> contacts = agenda.getContacts();
    return new ArrayList<CardInfo>(contacts);
  }

  private List<ImageInfo> getAllMediaElements() {
    List<MediaElement> lastMediaElements = mediaGallery.getLatestMediaElements();
    return new ArrayList<ImageInfo>(lastMediaElements);
  }

  private List<IconInfo> getPreferences() {
    List<IconInfo> preferences = new LinkedList<IconInfo>();
    preferences.add(new IconInfo(R.string.close_session, R.drawable.icn_wink));
    preferences.add(new IconInfo(R.string.close_session, R.drawable.icn_wink));
    preferences.add(new IconInfo(R.string.close_session, R.drawable.icn_wink));
    preferences.add(new IconInfo(R.string.close_session, R.drawable.icn_wink));
    return preferences;
  }

  public interface View {

    void updateBackground(String imageUrl);

    void showMainInformation(List<CardInfo> favorites, List<CardInfo> conversations,
        List<CardInfo> contacts, List<ImageInfo> mediaElements, List<IconInfo> preferences);

    void showDefaultBackground();

    void closeView();

    void cancelPendingBackgroundUpdates();
  }
}
