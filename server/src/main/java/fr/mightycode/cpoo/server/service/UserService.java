package fr.mightycode.cpoo.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import fr.mightycode.cpoo.server.model.UserData;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
  @Autowired // pour partager un userRepository commun aux autres services
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsManager userDetailsManager;
  private final HttpServletRequest httpServletRequest;

  /**
   * Store a new user (create a new user account)
   * Used in UserController.java (signup)
   *
   * @param login and others : all of UserData and FullUserDTOc
   */
  public void createUser(String login, int icon, String firstname, String lastname, LocalDate birthday, String address) {
    UserData user = new UserData(login,icon,firstname,lastname,birthday,address, null);
    userRepository.save(user);
  }

  /**
   * Delete an user account
   * Used in UserController.java (delete)
   *
   * @param login The username of the user to delete
   */
  public void deleteThisUser(String login) throws ResponseStatusException {
    UserData user = userRepository.findByLogin(login);
    userRepository.delete(user);
    if (user != null) {
      userRepository.delete(user);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
    }
  }

  public boolean signup(final String login, final String password) { //create account
    if (userDetailsManager.userExists(login)) //if already exists
      return false;
    final UserDetails user = new User(login, passwordEncoder.encode(password),
      List.of(new SimpleGrantedAuthority("ROLE_USER"))); //create user account
    userDetailsManager.createUser(user);
    return true;
  }

  public boolean signin(final String login, final String password) throws ServletException { //login
    final HttpSession session = httpServletRequest.getSession(false);
    if (session != null) //if already logged
      return false;
    httpServletRequest.login(login, password); //login session
    httpServletRequest.getSession(true);
    return true;
  }

  public void signout() throws ServletException { //logout session
    httpServletRequest.logout();
  }

  public int delete(String login, String password) {
    if (!userDetailsManager.userExists(login)) { //case user doesn't exist
      return 0;
    }else{
      UserDetails userDetails = userDetailsManager.loadUserByUsername(login);
      if (!passwordEncoder.matches(password, userDetails.getPassword())) { //case incorrect password
        return 1;
      }
      deleteThisUser(login); //to delete user from the database
      userDetailsManager.deleteUser(login); //ok, deletion
      return -1;
    }
  }

  /**
   * Retrieve the UserData associated to the given user
   * @param user The address of the user
   * @return The UserData
   */
  public UserData getUser(String user){
    return userRepository.findByLogin((getLogin(user)));
  }

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
