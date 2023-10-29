package fr.mightycode.cpoo.server.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import fr.mightycode.cpoo.server.service.RouterService;

public record MessageDTO(UUID msgID, String recipient, String content, String autorID, String autorAddress,
                         LocalDateTime date, boolean edited){

  public MessageDTO(RouterService.Message message){
    this(message.id(), message.recipient(), message.content(), message.author(), message.authorAddress(),
      message.date(), message.edited());
  }

}
