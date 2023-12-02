package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.dto.ContactProfileDTO;
import fr.mightycode.cpoo.server.model.UserData;
import fr.mightycode.cpoo.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
  @Autowired // pour partager un userRepository commun aux autres services
  private final UserRepository userRepository;

  /**
   * Retrieve a list of all users registered in the app
   *
   * @return The list of users (contacts)
   */
  public List<ContactProfileDTO> getAllContacts() {
    List<UserData> allUsers = userRepository.findAll();
    List<ContactProfileDTO> allContacts = new ArrayList();
    for (UserData user : allUsers) {
      ContactProfileDTO contact = new ContactProfileDTO(user);
      allContacts.add(contact);
    }
    return allContacts;
  }

  /**
   * Retrieve a given user in the list of registered users
   *
   * @return The user contact (profile)
   */
  public ContactProfileDTO getOneContact(String userID) {
    UserData user = userRepository.findByLogin(userID);
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found, doesn't exist in the app");
    }
    return new ContactProfileDTO(user);
  }
}
