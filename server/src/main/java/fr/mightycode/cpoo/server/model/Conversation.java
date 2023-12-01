package fr.mightycode.cpoo.server.model;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.dto.MessageDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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

  // The user who initiated the conversation
  @ToString.Exclude
  @Column(name = "user1", nullable = false)
  private String user1;

  @ToString.Exclude
  @Column(name = "user2", nullable = false)
  private String user2;

  @ToString.Exclude
  @Column(name = "timestamp", nullable = false)
  private long timestamp;

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
  public Conversation(String user1, String user2, UserData userData1, UserData userData2) {
    this.id = UUID.randomUUID();
    this.user1 = user1;
    this.user2 = user2;
    this.timestamp = 0;
    this.users = new ArrayList<>(Arrays.asList(userData1, userData2));
    this.messages = new ArrayList<>();
  }

  // If the interlocutor does not belong to pingpal
  public Conversation(String user1, String user2, UserData userData1) {
    this.id = UUID.randomUUID();
    this.user1 = user1;
    this.user2 = user2;
    this.timestamp = 0;
    this.users = new ArrayList<>(Collections.singletonList(userData1));
    this.messages = new ArrayList<>();
  }

  // If both interlocutors are part of the Pingpal domain
  public Conversation(ConversationDTO conversationDTO, UserData userData1, UserData userData2) {
    this.id = conversationDTO.id();
    this.user1 = conversationDTO.user1();
    this.user2 = conversationDTO.user2();
    this.timestamp = conversationDTO.timestamp();
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
    this.timestamp = conversationDTO.timestamp();
    this.users = new ArrayList<>(Collections.singletonList(userData1));

    // Create the list of messages
    for (MessageDTO messageDTO : conversationDTO.messagesDTOS()) {
      this.messages.add(new Message(messageDTO, this));
    }
  }

  // If the conversation is initiated by someone out of our domain
  public Conversation(String user1, String user2, UserData userData, Message message) {
    this.id = UUID.randomUUID();
    this.user1 = user1;
    this.user2 = user2;
    this.timestamp = message.getTimestamp();
    this.users = new ArrayList<>(Collections.singletonList(userData));
    this.messages = new ArrayList<>();
    this.messages.add(message);
  }
}
