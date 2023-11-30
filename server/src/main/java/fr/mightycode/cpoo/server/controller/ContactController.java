package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.service.ContactService;
import fr.mightycode.cpoo.server.dto.ContactProfileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin

public class ContactController {

    private final ContactService contactService;

    @GetMapping(value = "friends", produces = MediaType.APPLICATION_JSON_VALUE) //return all user informations
    public List<ContactProfileDTO> getAllUsers() {
        try {
            List<ContactProfileDTO> contacts = contactService.getAllContacts();
            return contacts;
        } catch (final Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "friend/{userID}", produces = MediaType.APPLICATION_JSON_VALUE) //return all user informations
    public ContactProfileDTO getOneContact(@PathVariable final String userID) {
        try {
            return contactService.getOneContact(userID);
        } catch (ResponseStatusException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw ex;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
            }
        }
    }
}