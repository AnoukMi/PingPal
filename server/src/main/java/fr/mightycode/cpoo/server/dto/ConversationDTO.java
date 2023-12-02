package fr.mightycode.cpoo.server.dto;

import fr.mightycode.cpoo.server.model.Conversation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record ConversationDTO(@NotEmpty UUID id,
                              @NotEmpty @Email String user1,
                              @NotEmpty @Email String user2,
                              Long timestamp,
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
      conversation.getTimestamp(),
      new ArrayList<>());
  }

  public void setMessagesDTOS(List<MessageDTO> messagesDTOS) {
    int actualSize = this.messagesDTOS.size();
    int newSize = messagesDTOS.size();
    if (actualSize == newSize) return;
    else {
      this.messagesDTOS.addAll(messagesDTOS);
    }
  }
}
