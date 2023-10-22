// package fr.mightycode.cpoo.server.controller;
//
//import fr.mightycode.cpoo.server.dto.ConversationDTO;
//import fr.mightycode.cpoo.server.service.ConversationService;
//import jakarta.servlet.ServletException;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.security.Principal;
//
//@RestController
//@RequestMapping("user/conversation")
//@AllArgsConstructor
//@CrossOrigin
//public class ConversationController {
//    @Value("pingpal")
//    private String serverDomain;
//
//
//    private final ConversationService conversationService;
//    private final MessageService messageService;
//
//
//    /**
//     * Retrieve all conversations of a given user
//     * @param user The user login
//     * @return The list of all conversations
//     */
//    @GetMapping(value = "conversations", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<ConversationDTO> listConversationsGet(final Principal user){
//        return conversationService.getConversations(user.getName());
//    }
//}
//
//    /**
//     * Create a new (empty) conversation with a given user
//     * @param user User ID to create a conversation with
//     * @return
//     */
//    @PostMapping(value = "{user}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<ConversationDTO> createEmptyConversation(@PathVariable String user) {
//        try {
//            ConversationDTO conversation = conversationService.createEmptyConversation(user);
//            return new ResponseEntity<>(conversation, HttpStatus.CREATED);
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // user not found
//        } catch (ConversationAlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // conversation already exists
//        }
//    }
//
//    /**
//     * Search and get an existing conversation with a given user
//     * @param user The given user login
//     * @return The conversationDTO that corresponds
//     */
//    @GetMapping(value = "{user}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ConversationDTO getOneConversation(@PathVariable String user){
//        try {
//            return conversationService.getOneConversation(user);
//        } catch(NoSuchElementException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//        }
//    }
//
//    /**
//     * Delete an existing conversation with a given user
//     * @param user The given user login
//     */
//    @DeleteMapping(value = "{user}")
//    public void deleteOneConversation(@PathVariable String user){
//        try {
//            return conversationService.deleteConversation(user);
//        } catch(NoSuchElementException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//        }
//    }
//

