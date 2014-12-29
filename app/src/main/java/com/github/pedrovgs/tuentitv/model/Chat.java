package com.github.pedrovgs.tuentitv.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Chat {

  private List<ConversationSummary> conversations;
  private Random random;

  public Chat() {
    conversations = new LinkedList<ConversationSummary>();
    loadConversations();
    random = new Random();
  }

  public List<ConversationSummary> getRecentConversations() {
    return conversations;
  }

  public ConversationSummary getConversationById(String conversationId) {
    ConversationSummary conversationSummary = null;
    for (ConversationSummary conversation : conversations) {
      if (conversationId.equals(conversation.getId())) {
        conversationSummary = conversation;
        break;
      }
    }
    return conversationSummary;
  }

  public ConversationSummary getLastUnreadConversation() {
    int randomPosition = random.nextInt(conversations.size());
    ConversationSummary randomConversation = conversations.get(randomPosition);
    return randomConversation;
  }

  private void loadConversations() {
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
        new ConversationSummary("Eduardo Ramirez", "https://imrl.tuenti.net/MekXWQRt9SETF706AA",
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
