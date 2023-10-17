package fr.mightycode.cpoo.server.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import fr.mightycode.cpoo.server.model.User;
import fr.mightycode.cpoo.server.repository.UserRepository;
import java.time.LocalDate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Store a new user (create a new user account)
     * Used in UserController.java (signup)
     *
     * @param all parameters of an user
     */
    public void createUser(String login, int icon, String firstname, String lastname, LocalDate birthday, String address) {
        List<User> friends = new Arraylist<User>();
        User user = new User(login,icon,firstname,lastname,birthday,address, null, friends);
        userRepository.save(user);
    }

    /**
     * Delete an user account
     * Used in UserController.java (delete)
     *
     * @param login The username of the user to delete
     */
    public void deleteUser(String login){
        userRepository.deleteById(login);
    }

    /**
     * Store a public (shared) short message
     *
     * @param message The message to store
     */
    public boolean saveSharedMessage(String message, String userId) {
        User user = userRepository.findByLogin(userId);
        if(message.length()<200){ //checke if not too long
            user.setSharedMessage(message); //update message
            userRepository.save(user); //save update to user
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete the current shared message of the logged user
     *
     * @param userId The login of the user
     */
    public boolean deleteSharedMessage(String userId) {
        User user = userRepository.findByLogin(userId);
        if (user.getSharedMessage() != null && !user.getSharedMessage().isEmpty()) { //checke if not already null or empty
            user.setSharedMessage(null); //reset message
            userRepository.save(user); //update user with resetting
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the current values of the logged user
     *
     * @param userId The login of the user
     */
    public FullUserDTO getUserFullProfile(String userId) {
        User user = userRepository.findByLogin(userId);
        FullUserDTO userFull = new FullUserDTO(
               user.getLogin(),
               "", //doesn't give password
               false,
               user.getIcon(),
               user.getFirstname(),
               user.getLastname(),
               user.getBirthday(),
               user.getAddress()
            );
       return userFull; //give all user informations
    }

    /**
     * Modify the current user profile informations
     *
     * @param all common parameters of FullUserDTO and User
     */
    public boolean editProfile(String userId, String password, int icon, String firstname, String lastname, LocalDate birthday, String address) throws ServletException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        if (passwordEncoder.matches(password, userDetails.getPassword())) { //checke password to securize editing
            User user = userRepository.findByLogin(userId);
            user.setIcon(icon);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setBirthday(birthday);
            user.setAddress(address);
            userRepository.save(user); //update user after setting all new informations
            return true;
        } else {
            return false;
        }
    }

}







