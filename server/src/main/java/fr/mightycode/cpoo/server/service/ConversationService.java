package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.dto.ConversationDTO;
import fr.mightycode.cpoo.server.dto.MessageDTO;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.UserData;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ConversationService {
  @Autowired
  private final ConversationRepository conversationRepository;
  @Autowired
  private final UserRepository userRepository;
  @Value("${cpoo.server.domain}")
  private String serverDomain;

  /**
   * Retrieve a list of all conversations with the current logged user
   *
   * @param user The current user logged in
   * @return The list of conversations
   */
  public List<ConversationDTO> getConversations(String user) {
    // Then find all of their conversations

    // First, find the conversations where user is the user1
    List<Conversation> conversations = conversationRepository.findByUser1(user);

    // Then, where user is the user2
    List<Conversation> conversations2 = conversationRepository.findByUser2(user);

    // Then concatenate both lists
    conversations.addAll(conversations2);
    // conversationRepository.findByUserDataOrderByLastMsgDateDesc(userData);

    List<ConversationDTO> conversationDTOS = new ArrayList<>();
    for (Conversation conversation : conversations) {
      conversationDTOS.add(new ConversationDTO(conversation));
    }

    return conversationDTOS;
  }


  /**
   * Create a new conversation with a given user
   *
   * @param user         The logged-in user
   * @param interlocutor The interlocutor address
   */
  public ConversationDTO createEmptyConversation(String user, String interlocutor) {
    // If a conversation already has user and interlocutor as its user1 and user2, we don't create a new one
    if (conversationRepository.findByUser1AndUser2(user, interlocutor).isPresent() ||
      conversationRepository.findByUser1AndUser2(interlocutor, user).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "A conversation with this user already exists");
    }
    Conversation conversation;

    UserData userData1 = userRepository.findByLogin(getLogin(user));

    if (interlocutor.endsWith('@' + serverDomain)) {
      // We naturally put the logged-in user as the user1 since it is the one that initiated the communication
      UserData userData2 = userRepository.findByLogin(getLogin(interlocutor));
      conversation = new Conversation(user, interlocutor, userData1, userData2);
    } else {
      conversation = new Conversation(user, interlocutor, userData1);
    }

    conversationRepository.save(conversation);
    return new ConversationDTO(conversation);
  }

  public void storeConversation(Conversation conversation) {
    conversationRepository.save(conversation);
  }


  /**
   * Search and get an existing conversation with a given user
   *
   * @param user         The current user logged in
   * @param interlocutor The address of the interlocutor
   * @return The conversationDTO that corresponds
   */
  public ConversationDTO getOneConversation(String user, String interlocutor) {
    // UserData userData = userRepository.findByLogin(user);
    List<Conversation> conversations1 = conversationRepository.findByUser1(user);
    List<Conversation> conversations2 = conversationRepository.findByUser2(user);
    ConversationDTO conversationDTO = null;

    // Find a conversation between the two users, user as user1 and interlocutor as user2
    for (Conversation conversation : conversations1) {
      if (conversation.getUser2().equals(interlocutor)) {
        // logger.info("Conversation trouvée : "+conversation);
        // logger.info("Messages associés : "+conversation.getMessages());
        conversationDTO = new ConversationDTO(conversation);

        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message msg : conversation.getMessages()) {
          messageDTOS.add(new MessageDTO(msg));
        }

        conversationDTO.setMessagesDTOS(messageDTOS);
      }
    }

    // Find a conversation between the two users, interlocutor as user1 and user as user2
    for (Conversation conversation : conversations2) {
      if (conversation.getUser1().equals(interlocutor)) {
        conversationDTO = new ConversationDTO(conversation);

        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message msg : conversation.getMessages()) {
          messageDTOS.add(new MessageDTO(msg));
        }

        conversationDTO.setMessagesDTOS(messageDTOS);
      }
    }


    if (conversationDTO == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found with this user");
    }
    return conversationDTO;
  }


  /**
   * Delete an existing conversation with a given user
   *
   * @param user         The logged-in user
   * @param interlocutor The address of the interlocutor
   */
  public boolean deleteConversation(String user, String interlocutor) {
    Optional<Conversation> conversationToDelete = conversationRepository.findByUser1AndUser2(user, interlocutor);

    // If we don't find the conversation, we look if the conversation exists but with the users inverted
    if (conversationToDelete.isEmpty()) {
      conversationToDelete = conversationRepository.findByUser1AndUser2(interlocutor, user);
      // If we don't find the conversation, the conversation doesn't exist
      if (conversationToDelete.isEmpty()) {
        return false;
      }
    }
    conversationRepository.delete(conversationToDelete.get());
    return true;
  }

  /**
   * Save the given message in the conversation
   *
   * @param user         The logged-in user
   * @param interlocutor The address of the interlocutor
   * @param message      The message to save
   */
  public void storeMessageInConversation(String user, String interlocutor, Message message) {
    Conversation conversation = this.findConversation(user, interlocutor);
    conversation.getMessages().add(message);

    // To update the conversation
    conversationRepository.save(conversation);
  }

  /**
   * Tell if a user is in our application by giving his username
   *
   * @param user The user login to test or his address
   * @return the login of the user if he is part of the application (is in the database), else null
   */
  public String logMember(String user) {
    String login = user;
    Pattern formatAddress = Pattern.compile("(.+)@pingpal"); //parenthèses pour capturer ce qui se trouve avant "@"
    Matcher matcher = formatAddress.matcher(user); // Objet Matcher pour effectuer la correspondance
    if (matcher.matches()) { //si on est au format d'adresse du domaine pingpal
      //Récupère la valeur du groupe capturé (la partie avant "@pingpal")
      login = matcher.group(1);
    }
    if (userRepository.findByLogin(login) == null) { //cherche le login dans BDD, retournera aussi null si c'est une adresse d'un autre domaine
      return null; //n'est pas membre
    }
    return login;
  }

  /**
   * Retrieve the login out of the address
   *
   * @param address The address
   * @return The login associated to the address
   */
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

  /**
   * Retrieve the conversation between user and interlocutor
   *
   * @param user         The logged-in user
   * @param interlocutor The address of the interlocutor
   * @return The Conversation between user and interlocutor
   */
  public Conversation findConversation(String user, String interlocutor) {
    Optional<Conversation> conversation = conversationRepository.findByUser1AndUser2(user, interlocutor);
    if (conversation.isEmpty()) {
      conversation = conversationRepository.findByUser1AndUser2(interlocutor, user);
      return conversation.orElse(null);
    } else {
      return conversation.get();
    }
  }


}

