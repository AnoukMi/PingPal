package fr.mightycode.cpoo.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.mightycode.cpoo.server.model.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String>{
  Conversation findByUser(String email);
}
