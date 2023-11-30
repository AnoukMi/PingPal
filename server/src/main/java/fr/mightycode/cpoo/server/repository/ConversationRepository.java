package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String> {
  Optional<Conversation> findByUser1AndUser2(String user1, String user2);

  List<Conversation> findByUser1(String user1);

  List<Conversation> findByUser2(String user2);
}
