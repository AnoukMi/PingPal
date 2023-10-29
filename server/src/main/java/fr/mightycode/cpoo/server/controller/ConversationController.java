 package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Value;

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
   * @param user UserID to create a conversation with
   * @return The created conversationDTO with the user
   */
    @PostMapping(value = "newConversation/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConversationDTO createEmptyConversation(@PathVariable final String user) {
      try{
        return conversationService.createEmptyConversation(user);
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
    String address=login;
    Pattern formatAddress = Pattern.compile(".+@.+"); // .+ signifie "n'importe quel caractère, une ou plusieurs fois"
    Matcher matcher = formatAddress.matcher(login); //objet Matcher pour effectuer la correspondance
    if (!matcher.matches()) { // Vérifie si login ne correspond pas au format d'address
      //càd le login est supposé être un user de l'application
      address=login+"@pingpal";
    try {
      return conversationService.getOneConversation(user.getName(), address);
    }catch (final Exception ex) {
       throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
     }
  }

  /**
   * Delete an existing conversation with a given user
   *
   * @param user The current user logged in
   * @param login The login with whom the conversation is exchanged
   */
  @DeleteMapping(value = "{login}")
  public void deleteOneConversation(final Principal user, @PathVariable String login) {
    try {
      conversationService.deleteConversation(user, login);
    }catch (final Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }
}


