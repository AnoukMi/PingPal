package fr.mightycode.cpoo.server.service;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import fr.mightycode.cpoo.server.model.Message;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class RouterService {

  /**
   * Type of messages exchanged between domain servers.
   */

  public record Message(
          UUID id,      // unique id of the message
          UUID idRecip,      // unique id of the message for the recipient
          String recipient, // recipient address
          String content, // content of the message
          String author,    // sender login
          String authorAddress, // sender address
          LocalDateTime date,  // date when the message was sent
          boolean edited // true if the message was edited by the sender
  ) {
  }

  /**
   * This interface is used by the router service to notify the domain server about incoming messages.
   * It must be implemented as a @Component or @Service, so that it can be injected automatically at service
   * creation time.
   */

  public interface MessageListener {

    /**
     * @return The name of the domain to listen.
     */

    String getServerDomain();

    /**
     * @return The URL of the router.
     */

    String getRouterUrl();

    /**
     * Notify the listener about an incoming message for its domain.
     *
     * @param message The incoming message.
     */
    void onMessageReceived(Message message);
  }

  @SuppressWarnings("NullableProblems")
  @Slf4j
  public static class RouterStompSessionHandler extends StompSessionHandlerAdapter {

    private final WebSocketStompClient webSocketStompClient;

    private final MessageListener messageListener;

    @Getter
    private StompSession stompSession;

    RouterStompSessionHandler(WebSocketStompClient webSocketStompClient, MessageListener messageListener) {
      this.webSocketStompClient = webSocketStompClient;
      this.messageListener = messageListener;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
      if (MimeTypeUtils.APPLICATION_JSON.equals(headers.getContentType()))
        return Message.class;
      throw new RuntimeException("Unexpected message type " + headers.getContentType());
    }

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders headers) {
      log.info("Client connected: headers {}", headers);
      this.stompSession = stompSession;
      stompSession.subscribe("/domain/" + messageListener.getServerDomain() + "/messages", this);
    }

    @Override
    public void handleFrame(StompHeaders headers, @Nullable Object payload) {
      log.debug("Client received: payload {}, headers {}", payload, headers);
      messageListener.onMessageReceived((Message) payload);
//      stompSession.acknowledge(headers, true);
    }

    @Override
    public void handleException(StompSession stompSession, @Nullable StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
      log.error("Client error: exception {}, command {}, payload {}, headers {}", exception.getMessage(), command, payload, headers);
    }

    @SneakyThrows
    @Override
    public void handleTransportError(StompSession stompSession, Throwable exception) {
      log.error("Client transport error: error {}", exception.getMessage());
      if (!stompSession.isConnected()) {
        Thread.sleep(2000);
        log.info("Trying to reconnect router {}...", messageListener.getRouterUrl());
        webSocketStompClient.connectAsync(messageListener.getRouterUrl(), this);
      }
    }
  }

  private final RouterStompSessionHandler routerStompSessionHandler;

  RouterService(MessageListener messageListener) {
    WebSocketStompClient webSocketStompClient = new WebSocketStompClient(new StandardWebSocketClient());
    webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
    routerStompSessionHandler = new RouterStompSessionHandler(webSocketStompClient, messageListener);

    // Start connection attempts immediately
    log.info("Trying to connect to router {}...", messageListener.getRouterUrl());
    webSocketStompClient.connectAsync(messageListener.getRouterUrl(), routerStompSessionHandler);
  }

  /**
   * Route a message to its recipient's domain server
   * (i.e. the domain specified in the 'to' property of the message).
   *
   * @param message The message to route
   */

  public void routeMessage(Message message) {
    log.info("Routing message {}", message);
    StompSession stompSession = routerStompSessionHandler.getStompSession();
    if (stompSession == null || !stompSession.isConnected()) {
      log.error("Not connected to router");
      return;
    }
    stompSession.send("/router/route", message);
  }
}
