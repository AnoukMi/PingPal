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
public class ConversationController {
    @Value("pingpal")
    private String serverDomain;


    private final ConversationService conversationService;
    private final MessageService messageService;


/**
 * Create a new (empty) conversation with a given user
 *
 */
    @PostMapping(value = "conversation/{user}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ConversationDTO> createEmptyConversation(@PathVariable String user) {
        try {
            ConversationDTO conversation = conversationService.createEmptyConversation(user);
            return new ResponseEntity<>(conversation, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // user not found
        } catch (ConversationAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // conversation already exists
        }
    }

/**
 * Delete a message already sent
 *
 */
    @DeleteMapping(value = "conversation/{user}/{msgID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSentMessage(@PathVariable string user, @PathVariable Long msgID){
        try {
            MessageDTO message = messageService.getMessageById(msgID);

            if(message == null){
                // Lancer une exception
            }else{
                messageService.deleteSentMessage(message);
            }
        }
    }

}