package fr.mightycode.cpoo.server.dto;

import fr.mightycode.cpoo.server.model.Conversation;

import java.time.LocalDateTime;

public record ConversationDTO(String userID, String peerAddress, LocalDateTime lastMessageDate) {

  public ConversationDTO(Conversation conversation){
    this(conversation.getUserData().getLogin(), conversation.getPeerAddress(), conversation.getLastMsgDate());
  }
}
