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

  public ConversationDTO {
    if (id == null || user1 == null || user2 == null) {
      throw new IllegalArgumentException("id, user1 and user2 cannot be null");
    }
  }

  public ConversationDTO(Conversation conversation) {
    this(conversation.getId(),
      conversation.getUser1(),
      conversation.getUser2(),
      conversation.getLastMsgDate(),
      new ArrayList<>());
  }
  public void setMessagesDTOS(List<MessageDTO> msgs){
    int actualSize = this.messagesDTOS.size();
    int newSize = msgs.size();
    if(actualSize == newSize) return;
    else {
      List<MessageDTO> toAdd = msgs.subList(actualSize, newSize);
      this.messagesDTOS.addAll(msgs);
    }
  }
}
