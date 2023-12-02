package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.PingpalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PingpalUserDetails implements UserDetails {

  private final PingpalUser pingpalUser;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority("ROLE_USER"));
    if ("admin".equals(pingpalUser.getLogin()))
      roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    return roles;
  }

  @Override
  public String getPassword() {
    return pingpalUser.getPassword();
  }

  @Override
  public String getUsername() {
    return pingpalUser.getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
