package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.FullUserDTO;
import fr.mightycode.cpoo.server.dto.UserDTO;
import fr.mightycode.cpoo.server.service.UserService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin

public class UserController {

  private final UserService userService;

  @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE) //create an account
  public void signup(@RequestBody final FullUserDTO user) {
    if (!userService.signup(user.login(), user.password()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    String birthday = user.birthday();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy"); //to convert string date to LocalDate type
    userService.createUser(user.login()/* , user.password() */, user.icon(), user.firstname(), user.lastname(),
      LocalDate.parse(birthday, formatter), user.address()); //to add new user in database
  }

  @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE) //login user
  public void signin(@RequestBody final UserDTO user) {
    try {
      if (!userService.signin(user.login(), user.password()))
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Already signed in");
    }
    catch (final ServletException ex) {
      if (ex.getCause() instanceof BadCredentialsException)
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials, incorrect password or login");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  @PostMapping(value = "signout") //logout user
  public void signout() {
    try {
      userService.signout();
    }
    catch (final ServletException ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  @DeleteMapping(value = "delete", consumes = MediaType.APPLICATION_JSON_VALUE) //delete user account
  public void deleteAccount(@RequestBody final UserDTO user) {
    int ret = userService.delete(user.login(), user.password()); // return 0 if user not found, 1 if password doesn't match
    if (ret == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
    }
    if (ret == 1) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
    }
  }
}
