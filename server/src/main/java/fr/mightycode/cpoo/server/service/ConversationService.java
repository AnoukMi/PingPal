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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

      //si l'user a des conversations mais que la liste est toujours vide, pb
      if (conversationDTOS.isEmpty()&&!userData.getConversations().isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversations not found");
      }

      return conversationDTOS;
    }


    /**
     * Create a new conversation with a given user
     * @param address The interlocutor address
     * @param user The current user
     */
    public ConversationDTO createEmptyConversation(String user, String address){
      if(conversationRepository.findById(user + address).isPresent()){ //user+recipient+0at0pingpal
        throw new ResponseStatusException(HttpStatus.CONFLICT, "A conversation with this user already exists");
      }

      //si aucune conv avec cet utilisateur n'existe, on la crée
      UserData user1 = userRepository.findByLogin(user); //le fait de charger l'user devrait màj automatiquement sa liste de conv
      Conversation conversation = new Conversation(user+address,address, LocalDateTime.now(),user1);
      //id unique composé des 2 utilisateurs (avec le domaine du second)
      conversationRepository.save(conversation);

      String interlocutor = logMember(address); //renvoie null si pas membre de Pingpal, sinon son username
      if (interlocutor!=null) { //si appartient à l'application, il faut aussi ajouter la conversation dans la BDD pour l'interlocuteur
        //seulement si n'existe pas déjà dans BDD du destinataire
        if(conversationRepository.findById(interlocutor + user + "0at0pingpal").isEmpty()) {
          UserData user2 = userRepository.findByLogin(interlocutor); //le fait de charger l'user devrait màj automatiquement sa liste de conv
          Conversation conversationDest = new Conversation(interlocutor+user+"0at0pingpal",user+"0at0pingpal",conversation.getLastMsgDate(),user2);
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
    public boolean deleteConversation(String loggedUser, String address) {
      Optional<Conversation> conversationToDelete = conversationRepository.findById(loggedUser + address);
      if (conversationToDelete.isEmpty()) {
        return false;
      }
      conversationRepository.delete(conversationToDelete.get());
      return true;
    }

  /**
   * Tell if a user is in our application by giving his username
   *
   * @param user The user login to test or his address
   * @return the login of the user if he is part of the application (is in the database), else null
   */
  public String logMember(String user){
    String login = user;
    Pattern formatAddress = Pattern.compile("(.+)0at0pingpal"); //parenthèses pour capturer ce qui se trouve avant "@"
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
