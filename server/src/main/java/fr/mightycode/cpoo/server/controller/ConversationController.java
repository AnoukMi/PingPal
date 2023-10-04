package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.UserDTO;
import fr.mightycode.cpoo.server.service.UserService;
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
    private final ConversationService conversationService;
    @PostMapping(value = "/{user}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ConversationDTO> createEmptyConversation(@PathVariable String user) {
        try {
            ConversationDTO conversation = conversationService.createEmptyConversation(user);
            return new ResponseEntity<>(conversation, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // user not found
        } catch (ConversationAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //conversation already exists
        }
    }
}