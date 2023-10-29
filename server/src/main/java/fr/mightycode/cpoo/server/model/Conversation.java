package fr.mightycode.cpoo.server.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


@Data
@Entity
@Table(name = "conversations")
public class Conversation {
  @Id
  @Column(name = "user", nullable = false)
  private String user;
  @Column(name = "peerAddress", nullable = false)
  private String peerAddress;
  @Column(name = "lastMessageDate", nullable = false)
  private LocalDate lastMsgDate;

  @ManyToOne
  @JoinColumn(name = "user_data_id")
  private UserData userData;

  @OneToMany(mappedBy = "conversation")
  private List<Message> messages;

  public Conversation(){

  }

  public Conversation(String user, String peerAddress, LocalDate lastMsgDate, UserData userData){
    this.user = user;
    this.peerAddress = peerAddress;
    this.lastMsgDate = lastMsgDate;
    this.userData = userData;
    this.messages = new ArrayList<>();
  }

  public String getUser(){
    return this.user;
  }
  public void setUser(String user){
    this.user = user;
  }
  public String getPeerAddress(){
    return this.peerAddress;
  }
  public void setPeerAddress(String peerAddress){
    this.peerAddress = peerAddress;
  }
  public LocalDate getLastMsgDate() {
    return this.lastMsgDate;
  }
  public void setLastMsgDate(LocalDate lastMsgDate){
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
