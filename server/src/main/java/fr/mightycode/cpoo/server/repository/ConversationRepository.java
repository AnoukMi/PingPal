package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.mightycode.cpoo.server.model.Conversation;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String>{
  Conversation findByPeerAddress(String email);
  void deleteByUser(String user);
  List<Conversation> findByUserData(UserData userData);
}
