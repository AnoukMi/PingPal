package fr.mightycode.cpoo.server.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

import java.util.UUID;
import java.util.List;

import fr.mightycode.cpoo.server.service.RouterService;

@Data
@Entity
@Table(name = "messages")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "msgID", nullable = false)
  private UUID msgID;
  @Column(name = "recipient", nullable = false)
  private String recipient;
  @Column(name = "content", nullable = false)
  private String content;
  @Column(name = "author", nullable = false)
  private String author;
  @Column(name = "authorAddress", nullable = false)
  private String authorAddress;
  @Column(name = "date", nullable = false)
  private LocalDate date;
  @Column(name = "edited", nullable = false)
  private boolean edited;

  @ManyToOne
  @JoinColumn(name = "conversation_id")
  private Conversation conversation;

  public Message(){

  }

  public Message(RouterService.Message message){
    this.msgID = message.id();
    this.recipient = message.recipient();
    this.content = message.content();
    this.author = message.author();
    this.authorAddress = message.authorAddress();
    this.date = message.date();
    this.edited = message.edited();
  }

//  public Message(Long msgID, String recipientID, String content, String autorID, String autorAddress,
//                 LocalDate date, boolean edited, Conversation conversation){
//    this.msgID = msgID;
//    this.recipient = recipientID;
//    this.content = content;
//    this.author = autorID;
//    this.date = date;
//    this.edited = edited;
//    this.conversation = conversation;
//  }

  // getters et setters
  public UUID getMsgId(){
    return this.msgID;
  }
  public void setMsgId(UUID id){
    this.msgID = id;
  }
  public String getRecipient(){
    return recipient;
  }
  public void setRecipient(String recipient){
    this.recipient = recipient;
  }
  public String getContent(){
    return this.content;
  }
  public void setContent(String content){
    this.content = content;
  }
  public String getAuthor(){
    return this.author;
  }
  public void setAuthor(String author){
    this.author = author;
  }
  public String getAuthorAddress(){
    return this.authorAddress;
  }
  public void setAuthorAddress(String authorAddress){
    this.authorAddress = authorAddress;
  }
  public LocalDate getDate() {
    return this.date;
  }
  public void setDate(LocalDate date){
    this.date = date;
  }
  public boolean isEdited() {
    return this.edited;
  }
  public void setEdited(boolean edited){
    this.edited = edited;
  }
  public Conversation getConversation() {
    return this.conversation;
  }
  public void setConversation(Conversation conv){
    this.conversation = conv;
  }
}
