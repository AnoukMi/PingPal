package fr.mightycode.cpoo.server;

import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.UserData;
import fr.mightycode.cpoo.server.repository.UserRepository;
import fr.mightycode.cpoo.server.service.ConversationService;
import fr.mightycode.cpoo.server.service.MessageService;
import fr.mightycode.cpoo.server.service.RouterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class DomainMessageListener implements RouterService.MessageListener {

  @Value("${cpoo.server.domain}")
  private String serverDomain;

  @Value("${cpoo.router.ws.url}")
  private String routerWSUrl;

  @Value("${cpoo.router.sse.url}")
  private String routerSSEUrl;

  private final MessageService messageService;
  private final ConversationService conversationService;
  private final UserRepository userRepository;

  @Override
  public String getServerDomain() {
    return serverDomain;
  }

  @Override
  public String getRouterWSUrl() {
    return routerWSUrl;
  }

  @Override
  public String getRouterSSEUrl() {
    return routerSSEUrl;
  }

  @Override
  public synchronized void onMessageReceived(RouterService.Message routerMessage) {

    log.info("Message received from router {}", routerMessage);

    // If the message is not already stored (it may have been routed using both WS and SSE)
    Message message = messageService.findById(routerMessage.id()).orElse(null);
    if (message != null) {
      log.warn("Message {} already stored... discarded");
      return;
    }

    Conversation conversation = conversationService.findConversation(routerMessage.to(), routerMessage.from());

    // If the conversation between the two users does not already exist
    if (conversation == null) {
      // If the message is sent by someone from another domain
      if (!routerMessage.from().endsWith("@pingpal") && routerMessage.to().endsWith("@pingpal")) {
        UserData userData = userRepository.findByLogin(getLogin(routerMessage.to()));
        conversation = new Conversation(routerMessage.from(), routerMessage.to(), userData);
        conversationService.storeConversation(conversation);

        // Store the message
        message = messageService.storeMessage(new Message(routerMessage, conversation));

        // Notify the message to the recipient (since he is part of the domain)
        messageService.notifyMessageTo(message, message.getTo());

        // Notify the message to the sender if he is part of the domain
        if (message.getFrom().endsWith("@" + serverDomain))
          messageService.notifyMessageTo(message, message.getFrom());
      }
    } else {
      // Store the message
      message = messageService.storeMessage(new Message(routerMessage, conversation));

      // Notify the message to the recipient (since he is part of the domain)
      messageService.notifyMessageTo(message, message.getTo());

      // Notify the message to the sender if he is part of the domain
      if (message.getFrom().endsWith("@" + serverDomain))
        messageService.notifyMessageTo(message, message.getFrom());
    }
  }


  /**
   * Retrieve the login out of the address
   *
   * @param address The address
   * @return The login associated to the address
   */
  private String getLogin(String address) {
    String result = "";
    String regex = "([^@]+)@.*";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(address);
    if (matcher.matches()) {
      // Extraire la partie avant le @ (groupe 1)
      result = matcher.group(1);
    }
    return result;
  }
}
