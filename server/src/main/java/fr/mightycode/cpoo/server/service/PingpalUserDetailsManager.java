package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.PingpalUser;
import fr.mightycode.cpoo.server.repository.PingpalUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PingpalUserDetailsManager implements UserDetailsManager {

  private final PingpalUserRepository pingpalUserRepository;

  public PingpalUserDetailsManager(PingpalUserRepository pingpalUserRepository, PasswordEncoder passwordEncoder) {
    this.pingpalUserRepository = pingpalUserRepository;
  }

  @Override
  public void createUser(UserDetails user) {
    PingpalUser pingpalUser = new PingpalUser();
    pingpalUser.setLogin(user.getUsername());
    pingpalUser.setPassword(user.getPassword());
    pingpalUserRepository.save(pingpalUser);
  }

  @Override
  public void updateUser(UserDetails user) {
    PingpalUser pingpalUser = pingpalUserRepository.findByLogin(user.getUsername());
    pingpalUser.setLogin(user.getUsername());
    pingpalUser.setPassword(user.getPassword());
    pingpalUserRepository.save(pingpalUser);
  }

  @Override
  public void deleteUser(String username) {
    pingpalUserRepository.delete(pingpalUserRepository.findByLogin(username));
  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {
  }

  @Override
  public boolean userExists(String username) {
    return pingpalUserRepository.existsByLogin(username);
  }

  // Implementation of the UserDetailsService interface

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    PingpalUser pingpalUser = pingpalUserRepository.findByLogin(username);
    if (pingpalUser == null)
      throw new UsernameNotFoundException(username);
    return new PingpalUserDetails(pingpalUser);
  }
}
