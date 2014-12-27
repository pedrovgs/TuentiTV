package com.github.pedrovgs.tuentitv.presenter;

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
import com.tuenti.tuentitv.R;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Class created to work as main view presenter. This presenter has all the responsibility related
 * to the main view presentation logic.
 *
 * Responsibilities:
 *
 * - Obtains a list of favorite contacts.
 *
 * - Obtains a list of recent conversations.
 *
 * - Show a list of all user's contacts sorted alphabetically.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainPresenter {

  private final Accounts accounts;
  private final Agenda agenda;
  private final MediaGallery mediaGallery;
  private final Chat chat;

  private View view;

  @Inject
  public MainPresenter(Accounts accounts, Agenda agenda, MediaGallery mediaGallery, Chat chat) {
    this.accounts = accounts;
    this.agenda = agenda;
    this.mediaGallery = mediaGallery;
    this.chat = chat;
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

  private List<IconInfo> getPreferences() {
    List<IconInfo> preferences = new LinkedList<IconInfo>();
    preferences.add(new IconInfo(R.string.close_session, R.drawable.icn_wink));
    preferences.add(new IconInfo(R.string.close_session, R.drawable.icn_wink));
    preferences.add(new IconInfo(R.string.close_session, R.drawable.icn_wink));
    preferences.add(new IconInfo(R.string.close_session, R.drawable.icn_wink));
    return preferences;
  }

  public void onCardInfoSelected(CardInfo cardInfo) {
    if (cardInfo != null) {
      view.updateBackground(cardInfo.getSecondaryImage());
    }
  }

  public void onImageInfoSelected(ImageInfo imageInfo) {
    if (imageInfo != null) {
      view.updateBackground(imageInfo.getImageUrl());
    }
  }

  public void onImageInfoClicked(ImageInfo item) {
    view.openImageView(item.getImageUrl());
  }

  public void onPreferencesSelected() {
    view.showDefaultBackground();
  }

  public void onSearchIconClicked() {
    view.openSearchView();
  }

  public void logout() {
    accounts.logout();
    view.closeAndGoToLoginActivity();
  }

  private List<CardInfo> getFavoriteContacts() {
    List<Contact> favorites = agenda.getFavorites();
    return new LinkedList<CardInfo>(favorites);
  }

  private List<CardInfo> getConversations() {
    List<ConversationSummary> conversations = chat.getConversations();
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

  public interface View {

    void updateBackground(String imageUrl);

    void showMainInformation(List<CardInfo> favorites, List<CardInfo> conversations,
        List<CardInfo> contacts, List<ImageInfo> mediaElements, List<IconInfo> preferences);

    void showDefaultBackground();

    void openSearchView();

    void closeAndGoToLoginActivity();

    void openImageView(String imageUrl);
  }
}
