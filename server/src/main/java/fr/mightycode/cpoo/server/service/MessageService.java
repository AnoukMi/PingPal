package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.UserData;
import lombok.RequiredArgsConstructor;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.List;
import java.util.ArrayList;

import fr.mightycode.cpoo.server.dto.MessageDTO;
import java.util.UUID;


import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.Conversation;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class MessageService {
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final ConversationRepository conversationRepository;
    @Autowired
    private final UserRepository userRepository;

    /**

    /**
     * Delete a message that's already sent.
     * @param msgID The message to delete
     */
    public void deleteSentMessage(UUID msgID) {
        Message message = messageRepository.findByMsgID(msgID);
      if (message == null) {
        throw new ResponseStatusException(HttpStatus.GONE, "The message is no more available, has been deleted");
      } else {
        messageRepository.delete(message);
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
        msg.setContent(modifiedContent);
      return new MessageDTO(msgID, msg.getRecipient(), modifiedContent, msg.getAuthor(),
          msg.getAuthorAddress(), msg.getDate(), true);
    }

    /**
     * Get all messages sent to or by a given user.
     *
     * @param interlocutor The user login of the interlocutor or his address
     * @param login The logged user login
     * @return the list of messages sent to or by the user
     */
    public List<MessageDTO> getMessages(String interlocutor, String login) {
        //liste de DTO de messages à retourner
        List<MessageDTO> msgDTOS = new ArrayList<>();
        UserData userData = userRepository.findByLogin(login);
        //récupère tous les headers de conversations de l'utilisateur connecté
        List<Conversation> conversations = conversationRepository.findByUserData(userData);
        Pattern formatAddress = Pattern.compile(".+@.+"); // .+ signifie "n'importe quel caractère, une ou plusieurs fois"
        Matcher matcher = formatAddress.matcher(interlocutor); //objet Matcher pour effectuer la correspondance
        String userAddress ="";
        if (matcher.matches()) { // Vérifie si login correspond au format d'adresse
            //si oui, userAddress prend directement la valeur de login
            userAddress = interlocutor;
        } else {
            // Sinon, login est l'username d'un user inscrit : on convertit avec l'addresse pingpal
            userAddress = interlocutor + "@" + "pingpal";
        }
        Conversation conversationToFind = null;
        //récupère parmi les conversations de l'utilisateur connecté, celle associée à l'interlocuteur souhaité
        for (Conversation conversation : conversations) {
            if (userAddress.equals(conversation.getPeerAddress())) {
                conversationToFind = conversation;
                break; //sort de la boucle for une fois que la conversation est trouvée
            }
        }
        if (conversationToFind == null) { //si aucune conversation n'a été trouvée
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No conversation with this user");
        } else {
            //récupère les messages associés à la conversation avec l'interlocuteur, ordonnés par le plus récent
            List<Message> messages = messageRepository.findAllByConversationOrderByDateDesc(conversationToFind);
            for(Message msg : messages) {
                MessageDTO msgDTO = new MessageDTO(msg.getMsgId(), msg.getRecipient(), msg.getContent(), msg.getAuthor(),
                        msg.getAuthorAddress(), msg.getDate(), msg.isEdited());
                msgDTOS.add(msgDTO); //remplit liste de messages convertis en DTO à retourner
            }
            if(msgDTOS.isEmpty()){
                throw new ResponseStatusException(HttpStatus.GONE, "The messages are no more available, have been deleted");
            }
            return msgDTOS;
        }
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
