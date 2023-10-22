package fr.mightycode.cpoo.router.controller;

import fr.mightycode.cpoo.router.model.Message;
import fr.mightycode.cpoo.router.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class RouteController {
  @Autowired
  RouterService routerService;
  @MessageMapping("/route")
  public void route(Message message, StompHeaderAccessor accessor) {
    routerService.routeMessage(message, accessor);
  }
}
