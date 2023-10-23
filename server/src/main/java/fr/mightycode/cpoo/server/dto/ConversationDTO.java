package fr.mightycode.cpoo.server.dto;

import fr.mightycode.cpoo.server.model.Conversation;

import java.time.LocalDate;

public record ConversationDTO(String userID, String peerAddress, LocalDate lastMessageDate) {

  public ConversationDTO(Conversation conversation){
    this(conversation.getUser(), conversation.getPeerAddress(), conversation.getLastMsgDate());
  }
  public ConversationDTO(String userID, String peerAddress, LocalDate lastMessageDate){
    this.userID = userID;
    this.peerAddress = peerAddress;
    this.lastMessageDate = lastMessageDate;
  }
}
