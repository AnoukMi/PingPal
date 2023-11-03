package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.UserData;
import lombok.RequiredArgsConstructor;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.List;
import java.util.ArrayList;

import fr.mightycode.cpoo.server.dto.MessageDTO;
import java.util.UUID;


import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.Conversation;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class MessageService {
    private ConversationService conversationService;
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final ConversationRepository conversationRepository;
    @Autowired
    private final UserRepository userRepository;


  /**
   * Get all messages sent to or by a given user.
   *
   * @param interlocutor The user login of the interlocutor or his address
   * @param login The current user logged in
   * @return the list of messages sent to or by the user
   */
  public List<MessageDTO> getMessages(String login, String interlocutor) {
    //liste de DTO de messages à retourner
    List<MessageDTO> msgDTOS = new ArrayList<>();
    String userAddress ="";
    if (isAddress(interlocutor)) { // Vérifie si interlocutor correspond au format d'adresse
      //si oui, userAddress prend directement la valeur de interlocutor
      userAddress = interlocutor;
    } else {
      // Sinon, login est l'username d'un user inscrit : on convertit avec l'addresse pingpal
      userAddress = interlocutor + "@" + "pingpal";
    }
    //récupère la conversation si elle existe
    Optional<Conversation> conversationOptional = conversationRepository.findById(login + userAddress);
      if (conversationOptional.isPresent()) {
          // La valeur est présente dans l'Optional
          Conversation conversationToFind = conversationOptional.get();
          //récupère les messages associés à la conversation avec l'interlocuteur, ordonnés par le plus récent
          List<Message> messages = messageRepository.findAllByConversationOrderByDateDesc(conversationToFind);
          for(Message msg : messages) {
              MessageDTO msgDTO = new MessageDTO(msg.getMsgId(), msg.getRecipient(), msg.getContent(), msg.getAuthor(),
                      msg.getAuthorAddress(), msg.getDate(), msg.isEdited());
              msgDTOS.add(msgDTO); //remplit liste de messages convertis en DTO à retourner
          }
          return msgDTOS;
      } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No conversation with this user");
      }
  }

    /**
     * Modify the content of a message that's already sent.
     * @param msgID The message to modify
     * @param modifiedContent The new content of the message
     */
    public MessageDTO modifySentMessage(UUID msgID, String modifiedContent){
        Message msg = messageRepository.findByMsgID(msgID);
        if(msg == null){
          throw new ResponseStatusException(HttpStatus.GONE, "The message is no more available, has been deleted");
        }
        msg.setContent(modifiedContent);
        msg.setEdited(true);
        String recipient = msg.getRecipient();
        String userPing =logMember(recipient);
        if(userPing!=null){ //modifie aussi pour le destinataire si appartient à l'appli
            Message messageRecip = messageRepository.findByMsgID(msg.getIdRecip());
            messageRecip.setContent(modifiedContent);
            messageRecip.setEdited(true);
        }
      return new MessageDTO(msgID, msg.getRecipient(), modifiedContent, msg.getAuthor(),
          msg.getAuthorAddress(), msg.getDate(), true);
    }


  /**
   * Delete a message that's already sent.
   * @param msgID The message to delete
   */
  public void deleteSentMessage(UUID msgID) {
    Message message = messageRepository.findByMsgID(msgID);
    if (message == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "msgID not found in the list of messages");
    } else {
      String recipient = message.getRecipient();
      String userPing =logMember(recipient);
      if(userPing!=null){ //supprime aussi pour le destinataire si appartient à l'appli
        Message messageRecip = messageRepository.findByMsgID(message.getIdRecip());
        messageRepository.delete(messageRecip);
      }
      messageRepository.delete(message);
    }
  }


    /**
     * Store a message in the DB.
     *
     * @param msg The message sent to store
     */
    public void storeMessage(Message msg){
        String login = logMember(msg.getAuthor());
        String recipient = msg.getRecipient();
        Optional<Conversation> conversationOptional = conversationRepository.findById(login+recipient);
        if (conversationOptional.isPresent()) {
            Conversation conv = conversationOptional.get();
            msg.setConversation(conv);
            conv.setLastMsgDate(msg.getDate());
            messageRepository.save(msg);
            conversationRepository.save(conv);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String userPing =logMember(recipient);
        if(userPing!=null){ //stocke aussi pour le destinataire si appartient à l'appli
            Conversation convRec = null;
            Optional<Conversation> conversationOptional2 = conversationRepository.findById(userPing+msg.getAuthorAddress());
            if (conversationOptional2.isPresent()) {
                convRec = conversationOptional2.get();
            }else{
                conversationService.createEmptyConversation(userPing,msg.getAuthorAddress());
                convRec = conversationRepository.findById(userPing+msg.getAuthorAddress()).get();
            }
            Message msgRecip = new Message(msg.getIdRecip(),msg.getMsgId(),msg.getRecipient(),msg.getContent(),
                    login,msg.getAuthorAddress(),msg.getDate(),msg.isEdited(),convRec);
            convRec.setLastMsgDate(msgRecip.getDate());
            messageRepository.save(msgRecip);
        }
    }

    /**
     * Tell if a user is in our application by giving his username
     *
     * @param user The user login to test or his address
     * @return the login of the user if he is part of the application (is in the database), else null
     */
    public String logMember(String user){
        String login = user;
        Pattern formatAddress = Pattern.compile("(.+)@pingpal"); //parenthèses pour capturer ce qui se trouve avant "@"
        Matcher matcher = formatAddress.matcher(user); // Objet Matcher pour effectuer la correspondance
        if (matcher.matches()) { //si on est au format d'adresse du domaine pingpal
            //Récupère la valeur du groupe capturé (la partie avant "@pingpal")
            login = matcher.group(1);
        }
        if(userRepository.findByLogin(login)==null){ //cherche le login dans BDD, retournera aussi null si c'est une adresse d'un autre domaine
            return null; //n'est pas membre
        }
        return login;
    }

    /**
     * Check if a string is an address
     *
     * @param log The user login to test as an address or not
     * @return true if it is an address
     */
    public boolean isAddress(String log){
        Pattern formatAddress = Pattern.compile(".+@.+");
        Matcher matcher = formatAddress.matcher(log); // Objet Matcher pour effectuer la correspondance
        if (matcher.matches()) { //si on est au format d'adresse
            return true;
        }
        return false;
    }
}
