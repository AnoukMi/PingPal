package fr.mightycode.cpoo.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    // Ensure that CORS is applied before authentication, so that OPTIONS requests can be processed unauthenticated.
    http.cors(withDefaults());

    // Disable Cross Site Request Forgery protection
    http.csrf(AbstractHttpConfigurer::disable);

    // Configure endpoint protection
    http.authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                    .requestMatchers(antMatcher("/user/signup")).permitAll()
                    .requestMatchers(antMatcher("/user/signin")).permitAll()
                    //.requestMatchers(antMatcher(HttpMethod.DELETE, "/user/*")).hasRole("ADMIN")
                    .requestMatchers(antMatcher("/error")).permitAll()
                    .anyRequest().authenticated());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {

    // Create a user account to be used by end-to-end tests
    UserDetails tester = User.withUsername("tester")
            .password(passwordEncoder.encode("tester"))
            .roles("USER")
            .build();

    // Create a user account to be used by end-to-end tests
    UserDetails alice = User.withUsername("alice")
            .password(passwordEncoder.encode("alice"))
            .roles("USER")
            .build();

    // Create a user account to be used by end-to-end tests
    UserDetails bob = User.withUsername("bob")
            .password(passwordEncoder.encode("bob"))
            .roles("USER")
            .build();

    // Create an administrator account
    UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("USER", "ADMIN")
            .build();

    return new InMemoryUserDetailsManager(tester, admin, alice, bob);
  }
}

