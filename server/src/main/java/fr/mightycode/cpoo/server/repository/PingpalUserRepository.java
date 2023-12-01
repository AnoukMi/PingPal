package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.PingpalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PingpalUserRepository extends JpaRepository<PingpalUser, String> {
  PingpalUser findByLogin(String login);

  boolean existsByLogin(String login);
}
