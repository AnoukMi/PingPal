package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {
  UserData findByLogin(String login);
}
