package fr.mightycode.cpoo.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    @Value("pingpal")
    private String serverDomain;

    private final MessageRepository messageRepository;

    /**
     * Return the message by its ID
     * @param msgID ID of the message
     * @return MessageDTO that corresponds to the given ID
     */
    private MessageDTO getMessageById(Long msgID){
        return messageRepository.getReferenceById(msgID);
    }

    /**
     * Delete a message that's already sent.
     * @param msg The message to delete
     */
    private void deleteSentMessage(MessageDTO msg) {
        messageRepository.deleteAllByIdIntBatch(msg.msgID);
    }

    /**
     * Modify the content of a message that's already sent.
     * @param msg The message to modify
     * @param modifiedContent The new content of the message
     */
    private void modifySentMessage(MessageDTO msg, String modifiedContent){
        msg.content = modifiedContent;
        msg.edited = true;
    }

    /**
     * Get all messages send to or by a given user.
     *
     * @param login The user login
     * @return the list of messages sent to or by the user
     */
    private List<MessageDTO> getMessages(String login) {
        String userAddress = login + "@" + serverDomain;
        return messageRepository.findByFromOrToIgnoreCaseOrderByTimestampDesc(userAddress, userAddress);
    }

    /**
     * Store a message in the DB.
     *
     * @param msg The message sent to store
     */
    private void storeMessage(MessageDTO msg){
        messageRepository.save(msg);
}


}