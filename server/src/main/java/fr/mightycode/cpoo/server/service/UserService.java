package fr.mightycode.cpoo.server.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;

  private final UserDetailsManager userDetailsManager;

  private final HttpServletRequest httpServletRequest;

  public boolean signup(final String login, final String password) { //create account
    if (userDetailsManager.userExists(login)) //if already exists
      return false;
    final UserDetails user = new User(login, passwordEncoder.encode(password), List.of(new SimpleGrantedAuthority("ROLE_USER"))); //create user account
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
      UserDetails userDetails = userDetailsService.loadUserByUsername(login);
      if (!passwordEncoder.matches(password, userDetails.getPassword())) { //case incorrect password
        return 1;
      }
      userDetailsManager.deleteUser(login); //ok, deletion
      return -1;
    }
  }
}
