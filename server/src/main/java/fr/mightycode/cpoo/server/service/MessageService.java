package fr.mightycode.cpoo.server.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

import fr.mightycode.cpoo.server.dto.MessageDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;


import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.Conversation;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Value("pingpal")
    private String serverDomain;

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    /**
     * Return the message by its ID
     * @param msgID ID of the message
     * @return MessageDTO that corresponds to the given ID
     */
    public MessageDTO getMessageById(UUID msgID){
      Message message = messageRepository.findByMsgID(msgID);

      if (message != null) {
        MessageDTO messageDTO = new MessageDTO(message);
        return messageDTO;
      } else {
        // Gérez le cas où aucune entité n'est trouvée avec l'ID spécifié
        return null; // ou lancez une exception, ou retournez un message d'erreur, selon vos besoins
      }
    }
    /**
     * Delete a message that's already sent.
     * @param msg The message to delete
     */
    public void deleteSentMessage(UUID msgID) {
      messageRepository.deleteByMsgID(msgID);
    }

    /**
     * Modify the content of a message that's already sent.
     * @param msg The message to modify
     * @param modifiedContent The new content of the message
     */
    public MessageDTO modifySentMessage(UUID msgID, String modifiedContent){
        Message msg = messageRepository.findByMsgID(msgID);
        MessageDTO modifiedMsg = new MessageDTO(msgID, msg.getRecipient(), modifiedContent, msg.getAuthor(),
          msg.getAuthorAddress(), msg.getDate(), true);
        return modifiedMsg;
    }

    /**
     * Get all messages send to or by a given user.
     *
     * @param login The user login
     * @return the list of messages sent to or by the user
     */
    public List<MessageDTO> getMessages(String login) {
        List<MessageDTO> msgDTOS = new ArrayList<>();
        String userAddress = login + "@" + serverDomain;
        Conversation conversation = conversationRepository.findByUser(userAddress);
        List<Message> messages = messageRepository.findByConversation(conversation);
        for(Message msg : messages) {
          MessageDTO msgDTO = new MessageDTO(msg.getMsgId(), msg.getRecipient(), msg.getContent(), msg.getAuthor(),
            msg.getAuthorAddress(), msg.getDate(), msg.isEdited());
          msgDTOS.add(msgDTO);
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
