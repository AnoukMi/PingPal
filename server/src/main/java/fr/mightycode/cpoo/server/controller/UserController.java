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
public class UserController {

  private final UserService userService;

  @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void signup(@RequestBody final UserDTO user) {
    if (!userService.signup(user.login(), user.password()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
  }

  @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void signin(@RequestBody final UserDTO user) {
    try {
      if (!userService.signin(user.login(), user.password()))
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Already signed in");
    } catch (final ServletException ex) {
      if (ex.getCause() instanceof BadCredentialsException)
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  @PostMapping(value = "signout")
  public void signout() {
    try {
      userService.signout();
    } catch (final ServletException ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  @PostMapping(value = "delete")
  public void delete(Principal user) {
    userService.delete();
  }
}
