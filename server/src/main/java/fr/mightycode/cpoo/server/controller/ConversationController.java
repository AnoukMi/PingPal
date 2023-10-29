 package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 @RestController
@RequestMapping("user/conversation")
@RequiredArgsConstructor
@CrossOrigin
public class ConversationController {

  private final ConversationService conversationService;


  /**
   * Retrieve all conversations of a given user (the current user)
   *
   * @param user The logged user
   * @return The list of all conversations
   */
  @GetMapping(value = "conversations", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ConversationDTO> listConversationsGet(final Principal user) {
    String loggedUser=user.getName();
    try {
      List<ConversationDTO> conversations = conversationService.getConversations(loggedUser);
      if (conversations == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem with the logged user");
      }
      return conversations;
    }
    catch (final Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }


  /**
   * Create a new (empty) conversation with a given user
   * @param user current user
   * @param interlocutor address or username of the interlocutor with whom create a new conversation
   * @return The created conversationDTO with the user
   */
    @PostMapping(value = "newConversation/{interlocutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConversationDTO createEmptyConversation(final Principal user, @PathVariable final String interlocutor) {
      String address = interlocutor;
      if (!isAddress(interlocutor)) { // Vérifie si login ne correspond pas au format d'address
        //càd le login est supposé être un user de l'application
        address = interlocutor + "@pingpal";
      }
      try{
        return conversationService.createEmptyConversation(user.getName(),address);
      }catch(final Exception ex){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
      }
    }

  /**
   * Search and get an existing conversation with a given user
   *
   * @param user The current user logged in
   * @param login The login with whom the conversation is exchanged
   * @return The conversationDTO that corresponds
   */
  @GetMapping(value = "{login}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ConversationDTO getOneConversation(final Principal user, @PathVariable String login) {
    String address = login;
    if (!isAddress(login)) { // Vérifie si login ne correspond pas au format d'address
      //càd le login est supposé être un user de l'application
      if(conversationService.logMember(login)==null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipient user does not exist");
      }
      address = login + "@pingpal"; //si existe on rend son adresse
    }
    try {
      return conversationService.getOneConversation(user.getName(), address);
    } catch (final Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  /**
   * Delete an existing conversation with a given user
   *
   * @param user The current user logged in
   * @param login The login user with whom the conversation is exchanged
   */
  @DeleteMapping(value = "{login}")
  public void deleteOneConversation(final Principal user, @PathVariable String login) {
    String address = login;
    if (!isAddress(login)) { // Vérifie si login ne correspond pas au format d'address
      //càd le login est supposé être un user de l'application
      address = login + "@pingpal";
    }
    try {
      conversationService.deleteConversation(user.getName(), address);
    }catch (final Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
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
       //si on est au format d'adresse
       return matcher.matches();
   }

}


