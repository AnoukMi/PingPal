package fr.mightycode.cpoo.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;


    private MessageDTO getMessageById(Long msgID){
        return messageRepository.getReferenceById(msgID);
    }

    private void deleteSentMessage(MessageDTO msg) {
        messageRepository.deleteAllByIdIntBatch(msg.msgID);
    }
}