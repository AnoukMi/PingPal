package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.PublicMessageDTO;
import fr.mightycode.cpoo.server.dto.FullUserDTO;
import fr.mightycode.cpoo.server.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin

public class ProfileController {

    private final ProfileService profileService;

    @PostMapping(value = "/shareMessage", consumes = MediaType.APPLICATION_JSON_VALUE) //post a new status
    public void postSharedMessage(@RequestBody String messageToShare, final Principal user) {
        String loggedUser = user.getName();
        try {
            if(!profileService.saveSharedMessage(messageToShare, loggedUser)) //false if message.length>=200
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The message is too long");
        } catch (final Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @DeleteMapping(value = "/shareMessage") //reset a shared message (make it null)
    public void deleteSharedMessage(final Principal user) {
        String loggedUser = user.getName();
        try {
            if (!profileService.deleteSharedMessage(loggedUser)) //false if the message is already null or empty
                throw new ResponseStatusException(HttpStatus.GONE, "The message is empty or has already been deleted");
        } catch (final Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE) //return all user informations
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FullUserDTO> getLoggedUserProfile(final Principal user) {
        String loggedUser = user.getName(); //give the login of the logged user
        try {
            FullUserDTO userProfile = profileService.getUserFullProfile(loggedUser);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (final Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PatchMapping(value = "/profile", consumes = MediaType.APPLICATION_JSON_VALUE) //edit the user informations
    public void editLoggedUserProfile(@RequestBody final FullUserDTO user) {
        try {
            String birthday = user.birthday();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //to convert string date to LocalDate type
            if(!profileService.editProfile(user.login(),
                    user.password(),
                    user.icon(),
                    user.firstname(),
                    user.lastname(),
                    LocalDate.parse(birthday, formatter),
                    user.address())){ //false if incorrect password
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials, editing not authorized");
            }
        } catch (final IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect types for user attributes");
        } catch (final Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }
}


