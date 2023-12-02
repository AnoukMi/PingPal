package fr.mightycode.cpoo.server.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pingpaluser")

public class PingpalUser {
  @Id
  @Column(nullable = false, unique = true)
  private String login;

  @Column(nullable = false)
  private String password;

}
