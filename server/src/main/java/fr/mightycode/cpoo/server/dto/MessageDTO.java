package fr.mightycode.cpoo.server.dto;

import java.time.LocalDate;
import java.util.UUID;

import fr.mightycode.cpoo.server.service.RouterService;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.Conversation;

public record MessageDTO(UUID msgID, String recipient, String content, String autorID, String autorAddress,
                         LocalDate date, boolean edited){

  public MessageDTO(RouterService.Message message){
    this(message.id(), message.recipient(), message.content(), message.author(), message.authorAddress(),
      message.date(), message.edited());
  }

  public MessageDTO(Message message){
    this(message.getMsgId(), message.getRecipient(), message.getContent(), message.getAuthor(),
      message.getAuthorAddress(), message.getDate(), message.isEdited());
  }

  public MessageDTO(UUID msgID, String recipient, String content, String autorID, String autorAddress,
                    LocalDate date, boolean edited){
    this.msgID = msgID;
    this.recipient = recipient;
    this.content = content;
    this.autorID = autorID;
    this.autorAddress = autorAddress;
    this.date = date;
    this.edited = edited;
  }
}
