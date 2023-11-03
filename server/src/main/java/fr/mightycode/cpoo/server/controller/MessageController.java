package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.UserData;
import fr.mightycode.cpoo.server.repository.UserRepository;
import fr.mightycode.cpoo.server.service.ConversationService;
import fr.mightycode.cpoo.server.service.RouterService;

import org.springframework.beans.factory.annotation.Autowired;

import fr.mightycode.cpoo.server.dto.MessageDTO;
import fr.mightycode.cpoo.server.service.MessageService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.lang.Exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import java.security.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("user/message")
@RequiredArgsConstructor
@CrossOrigin
public class MessageController extends TextWebSocketHandler {

    private final MessageService messageService;
    private final RouterService routerService;
    private final ConversationService conversationService;

    @Autowired
    private final UserRepository userRepository;


    /**
     * Retrieve all messages in a given conversation (with a given user)
     * @param user The current user logged in
     * @param userID The userID with whom to retrieve the messages
     * @return The list of all messages
     */
    @GetMapping(value = "{userID}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> listMessagesGet(final Principal user, @PathVariable final String userID) {
        String loggedUser = user.getName();
        try {
            return messageService.getMessages(userID, loggedUser);
        }catch(final Exception ex){
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

  /**
     * Send a new message to a given user
     */

    @PostMapping(value = "newMessage/{recipient}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO sendNewMessage(final Principal user, @PathVariable final String recipient,
                                     @RequestBody final String content) {
        String recipAddr=recipient;
        Pattern formatAddress = Pattern.compile(".+@.+"); // .+ signifie "n'importe quel caractère, une ou plusieurs fois"
        Matcher matcher = formatAddress.matcher(recipient); //objet Matcher pour effectuer la correspondance
        if (!matcher.matches()) { // Vérifie si login ne correspond pas au format d'adresse (càd le login est censé correspondre
            //à un user de l'application appartenant à la base de donnée)
            UserData userRecipient = userRepository.findByLogin(recipient);
            if(userRecipient==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipient user does not exist");
            }
            recipAddr=recipient+"@pingpal";
        }
        try {
            // Build a router message from the given information in parameters
            RouterService.Message routerMessage = new RouterService.Message(
            UUID.randomUUID(), UUID.randomUUID(),
            recipAddr,
            content,
            user.getName(),
            user.getName() + "@pingpal",
            LocalDateTime.now(),
            false
            );

            Message message = new Message(routerMessage);

            // Route the message
            routerService.routeMessage(routerMessage);

            // Store the message in the DB for both user and interlocutor
            messageService.storeMessage(message);

            // Return the message as a DTO
            return new MessageDTO(routerMessage);

        }catch (final Exception ex) {
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

    /**
     * Delete a message already sent
     */
    @DeleteMapping(value = "{msgID}")
    public void deleteSentMessage(@PathVariable final UUID msgID) {
      try{
        messageService.deleteSentMessage(msgID);
      }catch(final Exception ex){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
      }
    }

}
