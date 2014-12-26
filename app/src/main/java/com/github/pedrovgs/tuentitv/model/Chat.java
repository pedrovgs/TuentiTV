package com.github.pedrovgs.tuentitv.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Chat {

  private List<ConversationSummary> conversations;

  public Chat() {
    conversations = new LinkedList<ConversationSummary>();
    loadConversations();
  }

  public List<ConversationSummary> getConversations() {
    return conversations;
  }

  private void loadConversations() {
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
  }
}
