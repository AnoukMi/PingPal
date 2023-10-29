package fr.mightycode.cpoo.server.service;

import lombok.RequiredArgsConstructor;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

import fr.mightycode.cpoo.server.dto.MessageDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;


import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.Conversation;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class MessageService {

    @Value("pingpal")
    private String serverDomain;

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    /**

    /**
     * Delete a message that's already sent.
     * @param msgID The message to delete
     */
    public void deleteSentMessage(UUID msgID) {
      if (messageRepository.findByMsgID(msgID) == null) {
        throw new ResponseStatusException(HttpStatus.GONE, "The message is no more available, has been deleted");
      } else {
        messageRepository.deleteByMsgID(msgID);
      }
    }

    /**
     * Modify the content of a message that's already sent.
     * @param msgID The message to modify
     * @param modifiedContent The new content of the message
     */
    public MessageDTO modifySentMessage(UUID msgID, String modifiedContent){
        // TODO : gérer le fait que le msgID n'appartienne pas à la BDD
        // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "msgID not found in the list of messages");
        Message msg = messageRepository.findByMsgID(msgID);
        if(msg == null){
          throw new ResponseStatusException(HttpStatus.GONE, "The message is no more available, has been deleted");
        }
      return new MessageDTO(msgID, msg.getRecipient(), modifiedContent, msg.getAuthor(),
          msg.getAuthorAddress(), msg.getDate(), true);
    }

    /**
     * Get all messages sent to or by a given user.
     *
     * @param login The user login
     * @return the list of messages sent to or by the user
     */
    public List<MessageDTO> getMessages(String login) {
        List<MessageDTO> msgDTOS = new ArrayList<>();
        String userAddress = login + "@" + serverDomain;
        Conversation conversation = conversationRepository.findByPeerAddress(userAddress);
        List<Message> messages = messageRepository.findByConversation(conversation);
        for(Message msg : messages) {
          MessageDTO msgDTO = new MessageDTO(msg.getMsgId(), msg.getRecipient(), msg.getContent(), msg.getAuthor(),
            msg.getAuthorAddress(), msg.getDate(), msg.isEdited());
          msgDTOS.add(msgDTO);
        }
        if(msgDTOS.isEmpty()){
          throw new ResponseStatusException(HttpStatus.GONE, "The messages are no more available, have been deleted");
        }
        return msgDTOS;
    }

    /**
     * Store a message in the DB.
     *
     * @param msg The message sent to store
     */
    public void storeMessage(Message msg){
        messageRepository.save(msg);
    }
}
