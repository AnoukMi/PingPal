package fr.mightycode.cpoo.server.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class RouterServiceSSE implements RouterService {

  private final WebClient webClient;

  RouterServiceSSE(MessageListener messageListener) {

    // Create and configure the web client
    webClient = WebClient.create(messageListener.getRouterSSEUrl());

    // Create a message flux that retry the connection automatically on error
    final Flux<Message> messageFlux = webClient.get()
      .uri("/sse/{domain}", messageListener.getServerDomain())
      .retrieve()
      .bodyToFlux(Message.class)
      .doOnComplete(() -> log.warn("SSE stream complete"))
      .doOnError(err -> log.error("SSE stream error: {}", err.getMessage()))
      .retryWhen(Retry.fixedDelay(Long.MAX_VALUE, Duration.ofSeconds(2)))
      .repeat();

    // Subscribe to the message flux and send each message received to the message listener
    log.info("Opening SSE connection with router {} for domain {}", messageListener.getRouterSSEUrl(),
      messageListener.getServerDomain());
    messageFlux.subscribe(messageListener::onMessageReceived);
  }

  @SneakyThrows
  @Override
  public void routeMessage(Message message) {
    log.info("Routing message using SSE {}", message);
    webClient.post()
      .uri("/route")
      .contentType(APPLICATION_JSON)
      .bodyValue(message)
      .retrieve()
      .bodyToMono(Void.class)
      .block();
  }
}

