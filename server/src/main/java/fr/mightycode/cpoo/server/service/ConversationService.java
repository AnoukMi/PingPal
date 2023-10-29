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
     * @param user The current user logged in
     * @return The list of conversations
     */
    public List<ConversationDTO> getConversations(final Principal user){
      List<ConversationDTO> conversationDTOS = new ArrayList<>();
      UserData userData = userRepository.findByLogin(user.getName());
        List<Conversation> conversations = conversationRepository.findByUserData(userData);
        for(Conversation conv : conversations){
          ConversationDTO conversationDTO = new ConversationDTO(conv);
          conversationDTOS.add(conversationDTO);
        }
        return conversationDTOS;
    }

  /**
   * Search and get an existing conversation with a given user
   *
   * @param user The current user logged in
   * @param login The given user login
   * @return The conversationDTO that corresponds
   */
  public ConversationDTO getOneConversation(final Principal user, String login){
    UserData userData = userRepository.findByLogin(user.getName());
    List<Conversation> conversations = conversationRepository.findByUserData(userData);
    ConversationDTO conversationDTO = null;
    for(Conversation conversation : conversations){
      if(conversation.getUser().equals(login)){
        conversationDTO = new ConversationDTO(conversation);
      }
    }
    if(conversationDTO == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found");
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
     * @param login The given user login
     */
    public void deleteConversation(final Principal user, String login){
      ConversationDTO conversationDTOToDelete = getOneConversation(user, login);
      if(conversationDTOToDelete == null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation to delete not found");
      }
      conversationRepository.deleteByUser(login);
    }
}
