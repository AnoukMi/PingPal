package fr.mightycode.cpoo.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;

    private ConversationDTO createEmptyConversation(UserDTO user){
        
    }
}