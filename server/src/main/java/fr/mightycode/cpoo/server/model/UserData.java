package fr.mightycode.cpoo.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "users")
public class UserData {
  @Id
  @Column(name = "login", nullable = false)
  private String login; // username (unique)
  @Column(name = "icon", nullable = false)
  private int icon; //numéro d'image icone
  @Column(name = "firstname", nullable = false)
  private String firstname; //prénom
  @Column(name = "lastname", nullable = false)
  private String lastname; //nom de famille
  @Column(name = "birthday", nullable = false)
  private LocalDate birthday; // date de naissance
  @Column(name = "address", nullable = false)
  private String address; //addresse avec domaine
  @Column(name = "sharedMessage", length = 200)
  private String sharedMessage; //texte du statut partagé
  @Column(name = "showOnline")
  private boolean showOnline; // to show online status

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  //cascade pour que supprimer un user supprime l'amitié)
  @JoinTable(
    name = "friendship",
    joinColumns = @JoinColumn(name = "user_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "friend_id", nullable = true)
  )
  private List<UserData> friends; //liste d'amis de l'user

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL)
  // cascade pour que supprimer un user supprime toutes ses convs
  private List<Conversation> conversations;

  public UserData() {
    // Constructeur par défaut vide
    this.showOnline = false; // to not be null initially
  }

  public UserData(String login, int icon, String firstname, String lastname, LocalDate birthday, String address,
                  String sharedMessage) {
    this.login = login;
    this.icon = icon;
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthday = birthday;
    this.address = address;
    this.sharedMessage = sharedMessage;
    this.friends = new ArrayList<>();
    this.conversations = new ArrayList<>();
    this.showOnline = false;
  }

  public void setSharedMessage(String message) {
    this.sharedMessage = message;
  }

  public void activeOnline() {
    this.showOnline = true;
  }

  public void desactiveOnline() {
    this.showOnline = false;
  }
}
