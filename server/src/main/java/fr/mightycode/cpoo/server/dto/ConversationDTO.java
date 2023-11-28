package fr.mightycode.cpoo.server.dto;

import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ConversationDTO(@NotEmpty UUID id,
                              @NotEmpty @Email String user1,
                              @NotEmpty @Email String user2,
                              LocalDateTime lastMessageDate,
                              List<MessageDTO> messagesDTOS) {

  public ConversationDTO(Conversation conversation){
    this(conversation.getId(),
      conversation.getUser1(),
      conversation.getUser2(),
      conversation.getLastMsgDate(),
      conversation.getMessages().stream()
      .map(MessageDTO::new)
      .toList());
//    this.id = conversation.getId();
//    this.user1 = conversation.getUser1();
//    this.user2 = conversation.getUser2();
//    this.lastMessageDate = conversation.getLastMsgDate();
//    this.messagesDTOS = new ArrayList<>();
//    for(Message msg : conversation.getMessages()){
//      this.messagesDTOS.add(new MessageDTO(msg));
//    }
  }
}
