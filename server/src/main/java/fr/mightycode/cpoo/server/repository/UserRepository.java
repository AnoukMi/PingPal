package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {
  UserData findByLogin(String login);
  List<UserData> findAll();
}
