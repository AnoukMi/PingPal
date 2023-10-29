package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.UserRepository;
import fr.mightycode.cpoo.server.dto.ConversationDTO;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

    @Autowired
    private final ConversationRepository conversationRepository;
    @Autowired
    private final UserRepository userRepository;


    /**
     * Retrieve a list of all conversations with the current logged user
     * @param login The current user logged in
     * @return The list of conversations
     */
    public List<ConversationDTO> getConversations(String login){
      List<ConversationDTO> conversationDTOS = new ArrayList<>();
      UserData userData = userRepository.findByLogin(login);
      List<Conversation> conversations = conversationRepository.findByUserDataOrderByLastMsgDateDesc(userData);
      for(Conversation conv : conversations){
          ConversationDTO conversationDTO = new ConversationDTO(conv);
          conversationDTOS.add(conversationDTO);
      }
      return conversationDTOS;
    }

  /**
   * Search and get an existing conversation with a given user
   *
   * @param loggedUser The current user logged in
   * @param address The given user address (interlocutor)
   * @return The conversationDTO that corresponds
   */
  public ConversationDTO getOneConversation(String loggedUser, String address){
    UserData userData = userRepository.findByLogin(loggedUser);
    List<Conversation> conversations = conversationRepository.findByUserData(userData);
    ConversationDTO conversationDTO = null;
    for(Conversation conversation : conversations){
      if(conversation.getPeerAddress().equals(address)){
        conversationDTO = new ConversationDTO(conversation);
        break;
      }
    }
    if(conversationDTO == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found with this user");
    }
    return conversationDTO;
  }


    /**
     * Create a new conversation with a given user
     * @param login The interlocutor
     */
    public ConversationDTO createEmptyConversation(String login){
      Conversation conversation = new Conversation();
      conversation.setPeerAddress(login + "@pingpal");
      conversationRepository.save(conversation);
      return new ConversationDTO(conversation);
    }

    /**
     * Delete an existing conversation with a given user
     * @param address The given user (interlocutor) address
     * @param loggedUser The current user login
     */
    public void deleteConversation(String loggedUser, String address){
      UserData userData = userRepository.findByLogin(loggedUser);
      List<Conversation> conversations = conversationRepository.findByUserData(userData);
      Conversation conversationToDelete = null;
      for(Conversation conversation : conversations){
        if(conversation.getPeerAddress().equals(address)){
          conversationToDelete = conversation;
          break;
        }
      }
      if(conversationToDelete == null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found with this user");
      }
      conversationRepository.delete(conversationToDelete);
    }
}
