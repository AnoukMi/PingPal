package fr.mightycode.cpoo.server.model;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.dto.MessageDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;


@Data
@Entity
@Table(name = "conversations")
public class Conversation {

  // @ToString.Exclude to avoid the error
  // "SLF4J: Failed toString() invocation on an object of type [fr.mightycode.cpoo.server.model.Message]"
  @ToString.Exclude
  @Id
  private UUID id;

  @ToString.Exclude
  @Column(name = "user1", nullable = false)
  private String user1;

  @ToString.Exclude
  @Column(name = "user2", nullable = false)
  private String user2;

  @ToString.Exclude
  @Column(name = "lastMessageDate", nullable = false)
  private LocalDateTime lastMsgDate;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_conversation",
    joinColumns = @JoinColumn(name = "conversation_id"),
    inverseJoinColumns = @JoinColumn(name = "user_data_id"))
  private List<UserData> users; // List of 1 or 2 users involved in the conversation

  @ToString.Exclude
  @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
  // Cascade to also delete every messages if a conversation is deleted
  private List<Message> messages;

  public Conversation() {
  }

  // If both users are part of pingpal
  public Conversation(String user1, String user2, LocalDateTime lastMsgDate, UserData userData1, UserData userData2) {
    this.id = UUID.randomUUID();
    this.user1 = user1;
    this.user2 = user2;
    this.lastMsgDate = lastMsgDate;
    this.users = new ArrayList<>(Arrays.asList(userData1, userData2));
    this.messages = new ArrayList<>();
  }

  // If the interlocutor does not belong to pingpal
  public Conversation(String user1, String user2, LocalDateTime lastMsgDate, UserData userData1) {
    this.id = UUID.randomUUID();
    this.user1 = user1;
    this.user2 = user2;
    this.lastMsgDate = lastMsgDate;
    this.users = new ArrayList<>(Collections.singletonList(userData1));
    this.messages = new ArrayList<>();
  }

  // If both interlocutors are part of the Pingpal domain
  public Conversation(ConversationDTO conversationDTO, UserData userData1, UserData userData2) {
    this.id = conversationDTO.id();
    this.user1 = conversationDTO.user1();
    this.user2 = conversationDTO.user2();
    this.lastMsgDate = conversationDTO.lastMessageDate();
    this.users = new ArrayList<>(Arrays.asList(userData1, userData2));

    // Create the list of messages
    for (MessageDTO messageDTO : conversationDTO.messagesDTOS()) {
      this.messages.add(new Message(messageDTO, this));
    }
  }

  // If only one of the interlocutors is part of the Pingpal domain
  public Conversation(ConversationDTO conversationDTO, UserData userData1) {
    this.id = conversationDTO.id();
    this.user1 = conversationDTO.user1();
    this.user2 = conversationDTO.user2();
    this.lastMsgDate = conversationDTO.lastMessageDate();
    this.users = new ArrayList<>(Collections.singletonList(userData1));

    // Create the list of messages
    for (MessageDTO messageDTO : conversationDTO.messagesDTOS()) {
      this.messages.add(new Message(messageDTO, this));
    }
  }
}
