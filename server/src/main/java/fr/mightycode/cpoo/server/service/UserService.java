package fr.mightycode.cpoo.server.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;

  private final UserDetailsManager userDetailsManager;

  private final HttpServletRequest httpServletRequest;

  public void signup(final String login, final String password) {
    if (userDetailsManager.userExists(login))
      throw new IllegalArgumentException("User already exists");
    final UserDetails user = new User(login, passwordEncoder.encode(password), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    userDetailsManager.createUser(user);
  }

  public boolean signin(final String login, final String password) throws ServletException {
    final HttpSession session = httpServletRequest.getSession(false);
    if (session == null) {
      httpServletRequest.getSession(true);
      httpServletRequest.login(login, password);
      return true;
    }
    return false;
  }

  public boolean signout() throws ServletException {
    final HttpSession session = httpServletRequest.getSession(false);
    if (session == null)
      return false;
    httpServletRequest.logout();
    return true;
  }

  public UserDetails findUser(final String userName) {
    return userDetailsManager.loadUserByUsername(userName);
  }
}
