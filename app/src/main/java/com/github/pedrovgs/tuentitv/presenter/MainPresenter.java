package com.github.pedrovgs.tuentitv.presenter;

import com.github.pedrovgs.tuentitv.model.Accounts;
import com.github.pedrovgs.tuentitv.model.Agenda;
import com.github.pedrovgs.tuentitv.model.CardInfo;
import com.github.pedrovgs.tuentitv.model.Contact;
import com.github.pedrovgs.tuentitv.model.ConversationSummary;
import com.github.pedrovgs.tuentitv.model.ImageInfo;
import com.github.pedrovgs.tuentitv.model.MediaElement;
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

  private View view;

  @Inject public MainPresenter(Accounts accounts, Agenda agenda) {
    this.accounts = accounts;
    this.agenda = agenda;
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
    view.showMainInformation(favorites, conversations, contacts, mediaElements);
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
    List<CardInfo> conversations = new ArrayList<CardInfo>();
    conversations.add(new ConversationSummary("Android Developers",
        "http://www.androidguys.com/wp-content/uploads/2011/12/android-developer-logo.png",
        "Pull request sent!!"));
    conversations.add(
        new ConversationSummary("Andu Fratu", "https://imrl.tuenti.net/MewKTgRZKByd5oZ9AA",
            "Hey, do you have the test ready?"));
    conversations.add(
        new ConversationSummary("Carmen Barroso", "https://imrl.tuenti.net/MexNAAOXqRoOXZcYAA",
            "Bye!"));
    conversations.add(
        new ConversationSummary("Cesar Estébanez", "https://imrl.tuenti.net/MeqsBwSymhO82EB9AA",
            "Hey, do you have the test ready?"));
    conversations.add(
        new ConversationSummary("Eduardo Ramirez", "https://imrl.tuenti.net/Mef8vgRt9SHKr5U0AA",
            "Do you come to the kitchen??"));
    conversations.add(
        new ConversationSummary("Laura Rus", "https://imrl.tuenti.net/MefvswOREWiM7ehEAA",
            "I have news!! :)"));
    conversations.add(
        new ConversationSummary("Roberto Fernández", "https://imrl.tuenti.net/Me2X1ATgzxoIlIjAAA",
            "I've a problem with one animation, can you help me?"));
    conversations.add(
        new ConversationSummary("David Santiago", "https://imrl.tuenti.net/Me4XWwQtq65VyIUVAA",
            "Beers after work?"));
    return conversations;
  }

  private List<CardInfo> getAllContacts() {
    List<Contact> contacts = agenda.getContacts();
    return new ArrayList<CardInfo>(contacts);
  }

  private List<ImageInfo> getAllMediaElements() {
    List<ImageInfo> mediaElements = new ArrayList<ImageInfo>();
    mediaElements.add(
        new MediaElement("Augmented Reality day :)", "https://imrl.tuenti.net/MeVNOAO1hS95RMX9AA"));
    mediaElements.add(
        new MediaElement("Madrid: 4 - BCN: 0 ", "https://imrl.tuenti.net/Me2XLwONIBZEgj76AA"));
    mediaElements.add(
        new MediaElement("Congrats!!!", "https://imrl.tuenti.net/MephbgPFZwIHmNz3AA"));
    mediaElements.add(
        new MediaElement("Happy hallowing!", "https://imrl.tuenti.net/MepO8AS-zo4tbPwOAA"));
    mediaElements.add(
        new MediaElement("I hate mondays :_(", "https://imrl.tuenti.net/Melh1wOmYV2w27UHAA"));
    mediaElements.add(new MediaElement("Take a look at the hat xD",
        "https://imrl.tuenti.net/MevCbwOhf0Kgn2LmAA"));
    mediaElements.add(
        new MediaElement("After work at Tuenti", "https://imrl.tuenti.net/MevCdQOhf0IzmO7bAA"));
    mediaElements.add(new MediaElement("Cloud phone team picture \\o/",
        "https://imrl.tuenti.net/Mej6IwPYc55jJ3OuAA"));
    mediaElements.add(
        new MediaElement("Design team building!", "https://imrl.tuenti.net/MekIYwS-zo6iTThqAA"));
    mediaElements.add(
        new MediaElement("Love is in the air <3", "https://imrl.tuenti.net/MegZXgOmYV2hznMJAA"));
    mediaElements.add(
        new MediaElement("Fifa 14 winners!", "https://imrl.tuenti.net/MeoEZARzE9_n9j5bAA"));
    mediaElements.add(
        new MediaElement("Murcian experts xD", "https://imrl.tuenti.net/MepPHAS-zo4pJD4gAA"));
    mediaElements.add(
        new MediaElement("Free donuts, yeah!", "https://imrl.tuenti.net/Meo8egTBiDawcvM3AA"));
    mediaElements.add(new MediaElement("Really???", "https://imrl.tuenti.net/Me0keARq1hWI_3FFAA"));
    return mediaElements;
  }

  public interface View {

    void updateBackground(String imageUrl);

    void showMainInformation(List<CardInfo> favorites, List<CardInfo> conversations,
        List<CardInfo> contacts, List<ImageInfo> mediaElements);

    void showDefaultBackground();

    void openSearchView();

    void closeAndGoToLoginActivity();
  }
}
