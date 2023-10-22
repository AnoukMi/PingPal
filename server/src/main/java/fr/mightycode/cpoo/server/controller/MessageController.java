package fr.mightycode.cpoo.server.controller;
import fr.mightycode.cpoo.server.service.RouterService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.mightycode.cpoo.server.dto.MessageDTO;
import fr.mightycode.cpoo.server.dto.MessageReducedDTO;
import fr.mightycode.cpoo.server.service.MessageService;
import fr.mightycode.cpoo.server.service.ConversationService;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.Exception;

import java.util.List;
import java.util.UUID;

import java.security.Principal;



@RestController
@RequestMapping("user/message")
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {
    @Value("pingpal")
    private String serverDomain;

    private final MessageService messageService;
    private final ConversationService conversationService;

  private final RouterService routerService;

    /**
     * Retrieve all messages in a given conversation
     * @return The list of all messages
     */
    @GetMapping(value = "message/{userID}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> listMessagesGet(final Principal user) {
        return messageService.getMessages(user.getName());
    }


    /**
     * Send a new message to a given user
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO sendNewMessage(final Principal user, @RequestBody final MessageReducedDTO postMessage) {

      long currentTimeMillis = System.currentTimeMillis();
      Instant instant = Instant.ofEpochMilli(currentTimeMillis);
      LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();


      // Build a router message from the MessageReducedDTO
      RouterService.Message routerMessage = new RouterService.Message(
        UUID.randomUUID(),
        postMessage.recipient(),
        postMessage.content(),
        user.getName(),
        user.getName() + "@" + serverDomain,
        localDate,
        false
      );

      // Route the message
      routerService.routeMessage(routerMessage);

      // Return the message as a DTO
      return new MessageDTO(routerMessage);
    }


    /**
     * Delete a message already sent
     */
    @DeleteMapping(value = "{msgID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSentMessage(@PathVariable final UUID msgID) {
      messageService.deleteSentMessage(msgID);
    }


  /**
     * Modify a message already sent
     */

  @PatchMapping(value = "{msgID}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public MessageDTO modifySentMessage(@PathVariable final UUID msgID, @RequestBody final String content) {
    return messageService.modifySentMessage(msgID, content);
  }
//  @PatchMapping(value = "{msgID}", consumes = MediaType.APPLICATION_JSON_VALUE,
//    produces = MediaType.APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<MessageDTO> modifySentMessage(@PathVariable String userID, @PathVariable Long msgID, @RequestBody final String modifiedContent) {
//    try {
//      MessageDTO message = messageService.getMessageById(msgID);
//      // Vérification si le message est null
//      if (message == null) {
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The message does not exist");
//      }
//      // Vous pouvez maintenant appeler la méthode de modification
//      messageService.modifySentMessage(message, modifiedContent);
//
//      // Si la modification réussit, vous pouvez renvoyer la nouvelle version du message
//      return new ResponseEntity<>(message, HttpStatus.OK);
//
//    } catch (NoSuchElementException e) {
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect types for user attributes");
//    }
//  }
}

