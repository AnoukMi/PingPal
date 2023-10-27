package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.UserRepository;
import fr.mightycode.cpoo.server.dto.ConversationDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

    @Autowired
    private final ConversationRepository conversationRepository;
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;


    /**
     * Retrieve a list of all conversations with the current logged user
     * @param login The user login
     * @return The list of conversations
     */
    public List<ConversationDTO> getConversations(String login){
      List<ConversationDTO> conversationDTOS = new ArrayList<>();
        UserData user = userRepository.findByLogin(login);
        List<Conversation> conversations = conversationRepository.findByUserData(user);
        for(Conversation conv : conversations){
          ConversationDTO conversationDTO = new ConversationDTO(conv);
          conversationDTOS.add(conversationDTO);
        }
        return conversationDTOS;
    }

//    public ConversationDTO getOneConversation(String login){
//        String userAddress = login + "@" + serverDomain;
//        // TODO
//    }

    /**
     * Create a new conversation with a given user
     * @param login The interlocutor
     */
    public ConversationDTO createEmptyConversation(String login){
      String recipientAddress = login + "@"; // +serverDomain
      return new ConversationDTO(login, recipientAddress, null);
    }

    /**
     * Delete an existing conversation with a given user
     * @param login The given user login
     */
    public void deleteConversation(String login){
        conversationRepository.deleteByUser(login);
    }
}
