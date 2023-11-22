//package fr.mightycode.cpoo.server.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.UUID;
//
//import fr.mightycode.cpoo.server.service.RouterService;
//
//@Data
//@Entity
//@Table(name = "messages")
//public class Message {
//  @Id
//  @Column(name = "msgID", nullable = false)
//  private UUID msgID;
//  @Column(name = "idRecip", nullable = false)
//  private UUID idRecip; //the ID of the same message in the recipient BDD
//  @Column(name = "recipient", nullable = false)
//  private String recipient;
//  @Column(name = "content", nullable = false)
//  private String content;
//  @Column(name = "author", nullable = false)
//  private String author;
//  @Column(name = "authorAddress", nullable = false)
//  private String authorAddress;
//  @Column(name = "date", nullable = false)
//  private LocalDateTime date;
//  @Column(name = "edited", nullable = false)
//  private boolean edited;
//
//  @ManyToOne
//  @JoinColumn(name = "id")
//  private Conversation conversation;
//
//  public Message(){
//
//  }
//
//  public Message(RouterService.Message message){
//    this.msgID = message.id();
//    this.idRecip =  UUID.randomUUID();
//    this.recipient = message.to();
//    this.content = message.body();
//    this.author = message.from();
//    this.authorAddress = message.from();
//    Instant instant = Instant.ofEpochMilli(message.timestamp());
//    this.date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
//    this.edited = false;
//    this.conversation=null;
//  }
//
//  public Message(UUID msgID,UUID idRecip, String recipientID, String content, String autorID, String autorAddress,
//                 LocalDateTime date, boolean edited, Conversation conversation){
//    this.msgID = msgID;
//    this.idRecip = idRecip;
//    this.recipient = recipientID;
//    this.content = content;
//    this.author = autorID;
//    this.authorAddress = autorAddress;
//    this.date = date;
//    this.edited = edited;
//    this.conversation = conversation;
//  }
//
//  // getters et setters
//  public UUID getMsgId(){
//    return this.msgID;
//  }
////  public void setMsgId(UUID id){
////    this.msgID = id;
////  }
//
//  public UUID getIdRecip(){
//    return this.idRecip;
//  }
//  public void setIdRecip(UUID id){
//    this.idRecip = id;
//  }
//
//  public String getRecipient(){
//    return recipient;
//  }
//  public void setRecipient(String recipient){
//    this.recipient = recipient;
//  }
//  public String getContent(){
//    return this.content;
//  }
//  public void setContent(String content){
//    this.content = content;
//  }
//  public String getAuthor(){
//    return this.author;
//  }
//  public void setAuthor(String author){
//    this.author = author;
//  }
//  public String getAuthorAddress(){
//    return this.authorAddress;
//  }
//  public void setAuthorAddress(String authorAddress){
//    this.authorAddress = authorAddress;
//  }
//  public LocalDateTime getDate() {
//    return this.date;
//  }
//  public void setDate(LocalDateTime date){
//    this.date = date;
//  }
//  public boolean isEdited() {
//    return this.edited;
//  }
//  public void setEdited(boolean edited){
//    this.edited = edited;
//  }
//  public Conversation getConversation() {
//    return this.conversation;
//  }
//  public void setConversation(Conversation conv){
//    this.conversation = conv;
//  }
//}

package fr.mightycode.cpoo.server.model;

import fr.mightycode.cpoo.server.service.RouterService;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "messages")
public class Message {

  @Id
  private UUID id;        // unique id of the message

  @Column(name = "timestamp", nullable = false)
  private long timestamp; // timestamp of the message

  @Column(name = "`from`", nullable = false)
  private String from;    // sender address

  @Column(name = "`to`", nullable = false)
  private String to;      // recipient address

  @Column(name = "type", nullable = false)
  private String type;    // MIME type of the body

  @Column(name = "body", nullable = false)
  private String body;    // body (BASE64 encoded for binary types)

  public Message() {
  }

  public Message(RouterService.Message routerMessage) {
    this.id = routerMessage.id();
    this.timestamp = routerMessage.timestamp();
    this.from = routerMessage.from();
    this.to = routerMessage.to();
    this.type = routerMessage.type();
    this.body = routerMessage.body();
  }
}


