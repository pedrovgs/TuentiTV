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
package com.github.pedrovgs.tuentitv.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Main class related to chat feature. This class contains the responsibility to return recent
 * conversations, search conversations by id and return last unread conversations when needed.
 * All the data this class returns is mocked for this sample.
 *
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

  /**
   * @return return recent conversations related to the logged user.
   */
  public List<ConversationSummary> getRecentConversations() {
    return conversations;
  }

  /**
   * @return conversations that matches with the conversation id passed as parameter.
   */
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

  /**
   * @return last unread conversation related to the logged user.
   */
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
