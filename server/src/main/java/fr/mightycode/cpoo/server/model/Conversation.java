package fr.mightycode.cpoo.server.model;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
@Entity
@Table(name = "conversations")
public class Conversation {

  @Id
  private UUID id;

  @Column(name = "user1", nullable = false)
  private String user1;

  @Column(name = "user2", nullable = false)
  private String user2;

  @Column(name = "lastMessageDate", nullable = false)
  private LocalDateTime lastMsgDate;

  @ManyToMany
  @JoinTable(
    name = "user_conversation",
    joinColumns = @JoinColumn(name = "conversation_id"),
    inverseJoinColumns = @JoinColumn(name = "user_data_id"))
  private List<UserData> users; // List of 2 users involved in the conversation

  @Getter
  @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL) //cascade pour que supprimer une conv supprime tous les messages appartenant
  private List<Message> messages;

  public Conversation(){
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

  private String getLogin(String address) {
    String result = "";
    String regex = "([^@]+)@.*";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(address);
    if (matcher.matches()) {
      // Extraire la partie avant le @ (groupe 1)
      result = matcher.group(1);
    }
    return result;
  }
}
