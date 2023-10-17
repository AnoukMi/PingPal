package fr.mightycode.cpoo.server.model;

import fr.mightycode.cpoo.server.service.RouterService;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "users")
public class User {
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
            nullable = true,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends; //liste d'amis de l'user

}