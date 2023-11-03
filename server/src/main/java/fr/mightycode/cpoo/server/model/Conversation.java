package fr.mightycode.cpoo.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;


@Data
@Entity
@Table(name = "conversations")
public class Conversation {
  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "peerAddress", nullable = false)
  private String peerAddress;
  @Column(name = "lastMessageDate", nullable = true)
  private LocalDateTime lastMsgDate;

  @ManyToOne
  @JoinColumn(name = "user_data_id")
  private UserData userData;

  @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL) //cascade pour que supprimer une conv supprime tous les messages appartenant
  private List<Message> messages;

  public Conversation(){
  }

  public Conversation(String id, String peerAddress, LocalDateTime lastMsgDate, UserData userData){
    this.id = id;
    this.peerAddress = peerAddress;
    this.lastMsgDate = lastMsgDate;
    this.userData = userData;
    this.messages = new ArrayList<>();
  }


  public String getId(){
    return this.id;
  }
  public void setId(String id){
    this.id = id;
  }
  public String getPeerAddress(){
    return this.peerAddress;
  }
  public void setPeerAddress(String peerAddress){
    this.peerAddress = peerAddress;
  }
  public LocalDateTime getLastMsgDate() {
    return this.lastMsgDate;
  }
  public void setLastMsgDate(LocalDateTime lastMsgDate){
    this.lastMsgDate = lastMsgDate;
  }
  public UserData getUserData() {
    return this.userData;
  }
  public void setUserData(UserData userData){
    this.userData = userData;
  }
  public List<Message> getMessages(){
    return this.messages;
  }
  public void setMessages(List<Message> messages){
    this.messages = messages;
  }
}
