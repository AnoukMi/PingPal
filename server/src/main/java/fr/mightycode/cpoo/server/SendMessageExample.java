package fr.mightycode.cpoo.server;

import fr.mightycode.cpoo.server.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SendMessageExample {

  private static final Logger logger = LoggerFactory.getLogger(SendMessageExample.class);

  private int i = 0;

  @Autowired
  RouterService routerService;

  // Build Merkle blocks every 30s
//  @Scheduled(cron = "* * * * * ?")
  public void ping() {
    try {
      RouterService.Message message = new RouterService.Message("alice@acme", "bob@acme",
        "This is message " + i++ + " from alice@acme to bob@acme");
      routerService.routeMessage(message);
    } catch (Exception e) {
      logger.error("Cannot send message", e);
    }
  }
}
