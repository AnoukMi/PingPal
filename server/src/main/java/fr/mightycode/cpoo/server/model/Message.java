package fr.mightycode.cpoo.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import fr.mightycode.cpoo.server.service.RouterService;

@Data
@Entity
@Table(name = "messages")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "msgID", nullable = false)
  private UUID msgID;
  @Column(name = "idRecip", nullable = false)
  private UUID idRecip; //the ID of the same message in the recipient BDD
  @Column(name = "recipient", nullable = false)
  private String recipient;
  @Column(name = "content", nullable = false)
  private String content;
  @Column(name = "author", nullable = false)
  private String author;
  @Column(name = "authorAddress", nullable = false)
  private String authorAddress;
  @Column(name = "date", nullable = false)
  private LocalDateTime date;
  @Column(name = "edited", nullable = false)
  private boolean edited;

  @ManyToOne
  @JoinColumn(name = "conversation_id")
  private Conversation conversation;

  public Message(){

  }

  public Message(RouterService.Message message){
    this.msgID = message.id();
    this.idRecip = message.idRecip();
    this.recipient = message.recipient();
    this.content = message.content();
    this.author = message.author();
    this.authorAddress = message.authorAddress();
    this.date = message.date();
    this.edited = message.edited();
  }

  public Message(UUID msgID,UUID idRecip, String recipientID, String content, String autorID, String autorAddress,
                 LocalDateTime date, boolean edited, Conversation conversation){
    this.msgID = msgID;
    this.idRecip = idRecip;
    this.recipient = recipientID;
    this.content = content;
    this.author = autorID;
    this.authorAddress = autorAddress;
    this.date = date;
    this.edited = edited;
    this.conversation = conversation;
  }

  // getters et setters
  public UUID getMsgId(){
    return this.msgID;
  }
//  public void setMsgId(UUID id){
//    this.msgID = id;
//  }

  public UUID getIdRecip(){
    return this.idRecip;
  }
  public void setIdRecip(UUID id){
    this.idRecip = id;
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
  public LocalDateTime getDate() {
    return this.date;
  }
  public void setDate(LocalDateTime date){
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
