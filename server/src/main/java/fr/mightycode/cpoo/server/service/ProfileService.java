package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.dto.FullUserDTO;
import fr.mightycode.cpoo.server.model.UserData;
import fr.mightycode.cpoo.server.repository.UserRepository;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ProfileService {
  @Autowired // pour partager un userRepository commun aux autres services
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsManager userDetailsManager;

  /**
   * Store a public (shared) short message
   *
   * @param message The message to store
   */
  public boolean saveSharedMessage(String message, String userId) {
    UserData user = userRepository.findByLogin(userId);
    if (message.length() <= 45) { // check if not too long
      user.setSharedMessage(message); //update message
      userRepository.save(user); //save update to user
      return true;
    } else {
      return false;
    }
  }

  /**
   * Delete the current shared message of the logged user
   *
   * @param userId The login of the user
   */
  public boolean deleteSharedMessage(String userId) {
    UserData user = userRepository.findByLogin(userId);
    if (user.getSharedMessage() != null && !user.getSharedMessage().isEmpty()) { //checke if not already null or empty
      user.setSharedMessage(null); //reset message
      userRepository.save(user); //update user with resetting
      return true;
    } else {
      return false;
    }
  }

  /**
   * Return the current values of the logged user
   *
   * @param userId The login of the user
   */
  public FullUserDTO getUserFullProfile(String userId) {
    UserData user = userRepository.findByLogin(userId);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy"); //to convert LocalDate to String
    String birthdayString = user.getBirthday().format(formatter);
    FullUserDTO userFull = new FullUserDTO(
      user.getLogin(),
      "", //doesn't give password
      false,
      user.getIcon(),
      user.getFirstname(),
      user.getLastname(),
      birthdayString,
      user.getAddress()
    );
    return userFull; //give all user information
  }

  /**
   * Modify the current user profile information
   *
   * @param all common parameters of FullUserDTO and User
   */
  public boolean editProfile(String userId, String password, int icon, String firstname, String lastname, LocalDate birthday, String address) throws ServletException {
    UserDetails userDetails = userDetailsManager.loadUserByUsername(userId);
    if (!passwordEncoder.matches(password, userDetails.getPassword())) { // check password to securize editing
      return false;
    } else {
      UserData user = userRepository.findByLogin(userId);
      user.setIcon(icon);
      user.setFirstname(firstname);
      user.setLastname(lastname);
      user.setBirthday(birthday);
      user.setAddress(address);
      userRepository.save(user); //update user after setting all new information
      return true;
    }
  }

}







