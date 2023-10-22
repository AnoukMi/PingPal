package fr.mightycode.cpoo.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.dto.UserDTO;
import fr.mightycode.cpoo.server.dto.ConversationDTO;

import java.util.List;

@Service
@AllArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final String serverDomain;

    /**
     * Create a new conversation with a given user
     * @param user The interlocutor
     */
    // retourne une conversation vide ou pas ?
    private void createEmptyConversation(UserDTO user){

    }

    /**
     * Store the conversation in the DB.
     * @param conv The conversation to save
     */
//    private void storeConversation(ConversationDTO conv){
//        conversationRepository.save(conv);
//    }

    /**
     * Retrieve a list of all conversations with the current logged user
     * @param login The user login
     * @return The list of conversations
     */
//    public List<ConversationDTO> getConversations(String login){
//        String userAddress = login + "@" + serverDomain;
//        return messageRepository.findByFromOrToIgnoreCaseOrderByTimestampDesc(userAddress);
//    }

//    public ConversationDTO getOneConversation(String login){
//        String userAddress = login + "@" + serverDomain;
//        // TODO
//    }

    /**
     * Delete an existing conversation with a given user
     * @param login The given user login
     */
//    public void deleteConversation(String login){
//        String userAddress = login + "@" + serverDomain;
//        conversationRepository.delete(userAddress);
//    }
}
