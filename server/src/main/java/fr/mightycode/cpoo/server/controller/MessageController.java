package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.service.RouterService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;

import fr.mightycode.cpoo.server.dto.MessageDTO;
import fr.mightycode.cpoo.server.dto.MessageReducedDTO;
import fr.mightycode.cpoo.server.service.MessageService;

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

    private final RouterService routerService;

    /**
     * Retrieve all messages in a given conversation
     * @return The list of all messages
     */
    @GetMapping(value = "{userID}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> listMessagesGet(@PathVariable final String userID) {
        try {
            List<MessageDTO> messages = messageService.getMessages(userID);
          if(messages == null){
            throw new ResponseStatusException(HttpStatus.GONE, "The messages are no more available, have been deleted");
          }
          return messages;
        }catch(final Exception ex){
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }


    /**
     * Send a new message to a given user
     */
    @PostMapping(value = "newMessage", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO sendNewMessage(final Principal user, @RequestBody final MessageReducedDTO postMessage) {
      try {
        // A la place des lignes qui suivent, peut-être tester si le recipient existe dans la BDD
        if (postMessage.recipient() == null) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserID not found");
        }

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
      }catch (final Exception ex) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
      }
    }


    /**
     * Delete a message already sent
     */
    @DeleteMapping(value = "{msgID}")
    public void deleteSentMessage(@PathVariable final UUID msgID) {
      try{
        if(!messageService.deleteSentMessage(msgID)){
          throw new ResponseStatusException(HttpStatus.GONE, "The message is no more available, has been deleted");
        }
      }catch(final Exception ex){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
      }
    }


  /**
     * Modify a message already sent
     */
  @PatchMapping(value = "{msgID}", produces = MediaType.APPLICATION_JSON_VALUE)
  public MessageDTO modifySentMessage(@PathVariable final UUID msgID, @RequestBody final String content) {
    try{
      return messageService.modifySentMessage(msgID, content);
    }catch(final Exception ex){
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }
}
