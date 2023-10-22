package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.service.ConversationService;
import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("user")
@AllArgsConstructor
@CrossOrigin
public class MessageController {
    @Value("pingpal")
    private String serverDomain;

    private MessageService messageService;
    private int i = 0;

    /**
     * Retrieve all messages in a given conversation
     * @return The list of all messages
     */
    @GetMapping(value = "message/{userID}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> listMessagesGet(@PathVariable String userID) {
        return messageService.getMessages(userID);
    }

    /* Ou bien cette méthode si on utilise Principal user

    @GetMapping(value = "message/{userID}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> listMessagesGet(final Principal user) {
        return messageService.getMessages(user.getName());
    } */


    /**
     * Send a new message to a given user
     */
    @PostMapping(value = "message/{userID}/newMessage", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO sendNewMessage(@PathVariable String userID, @RequestBody MessageDTO message){
        try {
            RouterService.Message message = new RouterService.Message(userID, System.currentTimeMillis(), "alice@acme", "bob@acme",
                    MediaType.TEXT_PLAIN_VALUE);
            i++; // On peut peut-être utiliser ça pour augmenter l'ID du message?
            routerService.routeMessage(message);
        } catch (Exception e) {
            logger.error("Cannot send message", e);
        }
    }
    }
    /**
     * Delete a message already sent
     */
    @DeleteMapping(value = "message/{userID}/{msgID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSentMessage(@PathVariable String userID, @PathVariable Long msgID) {
        try {
            MessageDTO message = messageService.getMessageById(msgID);

            if (message == null) {
                throw new MessageNotFoundException(HttpStatus.NOT_FOUND, "The message does not exist");
            } else {
                messageService.deleteSentMessage(message);
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect types for user attributes");
        }
    }

    /**
     * Modify a message already sent
     */
    @PatchMapping(value = "message/{userID}/{msgID}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void modifySentMessage(@PathVariable String userID, @PathVariable Long msgID, @RequestBody String modifiedContent) {
        try {
            MessageDTO message = messageService.getMessageById(msgID);

            if (message == null) {
                throw new MessageNotFoundException(HttpStatus.NOT_FOUND, "The message does not exist");
            } else {
                messageService.modifySentMessage(message, modifiedContent);
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect types for user attributes");
        }
    }


}