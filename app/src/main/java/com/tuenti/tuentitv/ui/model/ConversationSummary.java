package com.tuenti.tuentitv.ui.model;

/**
 * Class created to represent a conversation summary. One conversation avatar, one
 * name and one last message.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class ConversationSummary implements CardInfo {

  private final String title;
  private final String conversationAvatar;
  private final String lastMessage;

  public ConversationSummary(String title, String conversationAvatar, String lastMessage) {
    this.title = title;
    this.conversationAvatar = conversationAvatar;
    this.lastMessage = lastMessage;
  }

  public String getTitle() {
    return title;
  }

  public String getConversationAvatar() {
    return conversationAvatar;
  }

  public String getLastMessage() {
    return lastMessage;
  }

  @Override public String getCardImageUrl() {
    return getConversationAvatar();
  }

  @Override public String getText() {
    return getLastMessage();
  }

  @Override public String getSecondaryImage() {
    return conversationAvatar;
  }
}
