package fr.mightycode.cpoo.server.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "conversations")
public class Conversation {
  @Id
  @Column(name = "user", nullable = false)
  private String user;
  @Column(name = "peerAddress", nullable = false)
  private String userAddress;
  @Column(name = "lastMessageDate", nullable = false)
  private LocalDate lastMsgDate;

  @ManyToOne
  @JoinColumn(name = "user_data_id")
  private UserData userData;

  @OneToMany(mappedBy = "conversation")
  private List<Message> messages;

}
