package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fr.mightycode.cpoo.server.model.Conversation;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String>{
  Conversation findByID(String id);
  @Query("SELECT c FROM Conversation c WHERE c.userData = :userData ORDER BY c.lastMsgDate DESC")
  List<Conversation> findByUserDataOrderByLastMsgDateDesc(UserData userData);
  @Query("SELECT c FROM Conversation c WHERE c.userData = :userData")
  List<Conversation> findByUserData(UserData userData);
}
