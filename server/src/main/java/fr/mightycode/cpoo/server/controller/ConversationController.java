package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.service.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("user/conversation")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class ConversationController {

  private final ConversationService conversationService;
  @Value("${cpoo.server.domain}")
  private String serverDomain;

  /**
   * Retrieve all conversations of the logged-in user
   *
   * @param user The logged users
   * @return The list of all conversations
   */
  @GetMapping(value = "conversations", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ConversationDTO> listConversationsGet(final Principal user) {
    try {
      log.info("Get conversations of the logged-in user");
      List<ConversationDTO> conversations = conversationService.getConversations(user.getName() + '@' + serverDomain);
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
   *
   * @param user         current user
   * @param interlocutor address or username of the interlocutor with whom create a new conversation
   * @return The created conversationDTO with the user
   */
  @PostMapping(value = "newConversation/{interlocutor}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ConversationDTO createEmptyConversation(final Principal user, @PathVariable final String interlocutor) {
    log.info("Create new conversation");
    return conversationService.createEmptyConversation(user.getName() + '@' + serverDomain, interlocutor);
  }

  /**
   * Search and get an existing conversation with a given user
   *
   * @param user         The current user logged in
   * @param interlocutor The address of the interlocutor
   * @return The conversationDTO that corresponds
   */
  @GetMapping(value = "{interlocutor}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ConversationDTO getOneConversation(final Principal user, @PathVariable String interlocutor) {
    try {
      return conversationService.getOneConversation(user.getName() + '@' + serverDomain, interlocutor);
    }
    catch (ResponseStatusException ex) {
      if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
        throw ex;
      } else {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
      }
    }
  }

  /**
   * Delete an existing conversation with a given user
   *
   * @param user         The current user logged in
   * @param interlocutor The login user with whom the conversation is exchanged
   */
  @DeleteMapping(value = "{interlocutor}")
  public void deleteOneConversation(final Principal user, @PathVariable String interlocutor) {
    if (!conversationService.deleteConversation(user.getName() + '@' + serverDomain, interlocutor)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found with this user");
    }
  }

}


