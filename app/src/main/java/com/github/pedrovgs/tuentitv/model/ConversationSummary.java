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

import com.github.pedrovgs.tuentitv.ui.data.CardInfo;

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

  @Override public String getId() {
    return title;
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
