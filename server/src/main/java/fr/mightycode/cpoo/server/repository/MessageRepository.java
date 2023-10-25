package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message, UUID>{
  List<Message> findByConversation(Conversation conversation);
  Message findByMsgID(UUID id);
  void deleteByMsgID(UUID id);
}
