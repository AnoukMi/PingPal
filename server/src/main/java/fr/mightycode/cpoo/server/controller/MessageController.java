package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.dto.MessageDTO;
import fr.mightycode.cpoo.server.dto.NewMessageDTO;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.service.ConversationService;
import fr.mightycode.cpoo.server.service.MessageService;
import fr.mightycode.cpoo.server.service.RouterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("user/message")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class MessageController {

  @Qualifier("routerServiceSSE")
  private final RouterService routerService;
  private final MessageService messageService;
  private final ConversationService conversationService;
  @Value("${cpoo.server.domain}")
  private String serverDomain;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public MessageDTO messagePost(final Principal user, @Valid @RequestBody final NewMessageDTO newMessage) {

    // Build a router message from the DTO
    RouterService.Message routerMessage = new RouterService.Message(
      UUID.randomUUID(),
      System.currentTimeMillis(),
      user.getName() + "@" + serverDomain,
      newMessage.to(),
      newMessage.type(),
      newMessage.body()
    );

    ConversationDTO conversationDTO = conversationService
      .getOneConversation(user.getName() + "@" + serverDomain, newMessage.to());

    Conversation conversation = conversationService.findConversation(user.getName() + "@" + serverDomain, newMessage.to());

    // Build a model message from the router message and the conversation
    Message message = new Message(routerMessage, conversation);

    // Timestamp of the conversation is the time of the last message sent/received
    conversation.setTimestamp(message.getTimestamp());

    // Also store the message in the right list of messages
    conversationService.storeMessageInConversation(user.getName() + "@" + serverDomain, newMessage.to(), message);

    // Build a messageDTO from the router message and the conversationDTO
    MessageDTO messageDTO = new MessageDTO(routerMessage, conversationDTO);

    // Route the message
    routerService.routeMessage(routerMessage);

    // Store the message and notify it to the sender only if the recipient is on another domain
    // (otherwise, the message will be anyway routed back to the server, and so will be stored and notified at that time)
    if (!routerMessage.to().endsWith("@" + serverDomain)) {
      messageService.storeMessage(message);
      messageService.notifyMessageTo(message, message.getFrom());
    }

    // Return the message as a DTO
    return messageDTO;
  }

  @GetMapping(value = "messages", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Message> messageGet(final Principal user) {
    // Get the message sink of the connected user and return it as a flux
    return messageService.getMessageSinkFor(user.getName() + "@" + serverDomain).asFlux();
  }
}


