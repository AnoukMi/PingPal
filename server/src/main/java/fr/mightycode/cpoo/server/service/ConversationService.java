package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.UserRepository;
import fr.mightycode.cpoo.server.dto.ConversationDTO;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationService {

    @Autowired
    private final ConversationRepository conversationRepository;
    @Autowired
    private final UserRepository userRepository;


    /**
     * Retrieve a list of all conversations with the current logged user
     * @param login The current user logged in
     * @return The list of conversations
     */
    public List<ConversationDTO> getConversations(String login) {
      UserData userData = userRepository.findByLogin(login);
      List<Conversation> conversations = conversationRepository.findByUserDataOrderByLastMsgDateDesc(userData);

      List<ConversationDTO> conversationDTOS = conversations.stream()
        .map(ConversationDTO::new)
        .collect(Collectors.toList());

      if (conversationDTOS.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Current user not found or conversations not found");
      }

      return conversationDTOS;
    }


    /**
     * Create a new conversation with a given user
     * @param address The interlocutor address
     * @param user The current user
     */
    public ConversationDTO createEmptyConversation(String user, String address){
      if(conversationRepository.findById(user + address).isPresent()){ //user+recipient+@pingpal
        throw new ResponseStatusException(HttpStatus.CONFLICT, "A conversation with this user already exists");
      }

      //si aucune conv avec cet utilisateur n'existe, on la crée
      Conversation conversation = new Conversation();
      conversation.setId(user+address); //id unique composé des 2 utilisateurs (avec le domaine du second)
      UserData user1 = userRepository.findByLogin(user); //le fait de charger l'user devrait màj automatiquement sa liste de conv
      conversation.setUserData(user1);
      conversation.setPeerAddress(address);
      conversationRepository.save(conversation);

      String interlocutor = logMember(address); //renvoie null si pas membre de Pingpal, sinon son username
      if (interlocutor!=null) { //si appartient à l'application, il faut aussi ajouter la conversation dans la BDD pour l'interlocuteur
        //seulement si n'existe pas déjà dans BDD du destinataire
        if(conversationRepository.findById(interlocutor + user + "@pingpal").isEmpty()) {
          Conversation conversationDest = new Conversation();
          conversationDest.setPeerAddress(user+"@pingpal");
          conversationDest.setId(interlocutor+user+"@pingpal"); //id unique composé des 2 utilisateurs (avec le domaine du second)
          UserData user2 = userRepository.findByLogin(interlocutor); //le fait de charger l'user devrait màj automatiquement sa liste de conv
          conversationDest.setUserData(user2);
          conversationRepository.save(conversationDest);
        }
      }

      return new ConversationDTO(conversation);
    }


  /**
   * Search and get an existing conversation with a given user
   *
   * @param loggedUser The current user logged in
   * @param address The given user address (interlocutor)
   * @return The conversationDTO that corresponds
   */
  public ConversationDTO getOneConversation(String loggedUser, String address){
    UserData userData = userRepository.findByLogin(loggedUser);
    List<Conversation> conversations = conversationRepository.findByUserData(userData);
    ConversationDTO conversationDTO = null;
    for(Conversation conversation : conversations){
      if(conversation.getPeerAddress().equals(address)){
        conversationDTO = new ConversationDTO(conversation);
        break;
      }
    }
    if(conversationDTO == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found with this user");
    }
    return conversationDTO;
  }


  /**
     * Delete an existing conversation with a given user
     * @param address The given user (interlocutor) address
     * @param loggedUser The current user login
     */
    public void deleteConversation(String loggedUser, String address){
      UserData userData = userRepository.findByLogin(loggedUser);
      List<Conversation> conversations = conversationRepository.findByUserData(userData);
      Conversation conversationToDelete = null;
      for(Conversation conversation : conversations){
        if(conversation.getPeerAddress().equals(address)){
          conversationToDelete = conversation;
          break;
        }
      }
      if(conversationToDelete == null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found with this user");
      }
      conversationRepository.delete(conversationToDelete);
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

}
