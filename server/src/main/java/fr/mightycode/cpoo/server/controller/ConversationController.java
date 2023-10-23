 package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.repository.UserRepository;
import fr.mightycode.cpoo.server.service.ConversationService;
import fr.mightycode.cpoo.server.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Value;

import java.security.Principal;

import java.util.List;
import java.util.NoSuchElementException;

 @RestController
@RequestMapping("user/conversation")
@RequiredArgsConstructor
@CrossOrigin
public class ConversationController {
  @Value("pingpal")
  private String serverDomain;

  private final ConversationService conversationService;
  private final MessageService messageService;
  private final UserRepository userRepository;


  /**
   * Retrieve all conversations of a given user
   *
   * @param user The user login
   * @return The list of all conversations
   */
  @GetMapping(value = "conversations", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ConversationDTO> listConversationsGet(final Principal user) {
    try {
      List<ConversationDTO> conversations = conversationService.getConversations(user.getName());
      if (conversations == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem");
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
    @PostMapping(value = "{user}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ConversationDTO createEmptyConversation(@PathVariable String user) {
      try{
        return conversationService.createEmptyConversation(user);
      }catch(final Exception ex){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
      }
    }

  /**
   * Search and get an existing conversation with a given user
   *
   * @param user The given user login
   * @return The conversationDTO that corresponds
   */
//  @GetMapping(value = "{user}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ConversationDTO getOneConversation(@PathVariable String user) {
//    try {
//      return conversationService.getOneConversation(user);
//    }
//    catch (NoSuchElementException e) {
//      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//    }
//  }

  /**
   * Delete an existing conversation with a given user
   *
   * @param user The given user login
   */
  @DeleteMapping(value = "{user}")
  public void deleteOneConversation(@PathVariable String user) {
    try {
      conversationService.deleteConversation(user);
    }
    catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
  }
}


