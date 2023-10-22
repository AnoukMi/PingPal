package fr.mightycode.cpoo.server.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import fr.mightycode.cpoo.server.model.Conversation;


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
    @Column(name = "sharedMessage", nullable = true, length = 200)
    private String sharedMessage; //texte du statut partagé

    @ManyToMany
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "friend_id", nullable = true)
    )
    private List<UserData> friends; //liste d'amis de l'user

  @OneToMany(mappedBy = "userData")
  private List<Conversation> conversations;

    public UserData() {
        // Constructeur par défaut vide (obligé)
    }
    public UserData(String login, int icon, String firstname, String lastname, LocalDate birthday, String address,
                    String sharedMessage, List<UserData> friends, List<Conversation> conversations) {
        this.login = login;
        this.icon = icon;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.address = address;
        this.sharedMessage = sharedMessage;
        this.friends = friends;
        this.conversations = conversations;
    }


    //getters et setters
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public int getIcon() {
        return icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSharedMessage() {
        return sharedMessage;
    }
    public void setSharedMessage(String sharedMessage) {
        this.sharedMessage = sharedMessage;
    }
    public List<UserData> getFriends() {
        return friends;
    }
    public void setFriends(List<UserData> friends) {
        this.friends = friends;
    }
    public List<Conversation> getConversations(){
      return conversations;
    }
    public void setConversations(List<Conversation> conversations){
      this.conversations = conversations;
    }
}
