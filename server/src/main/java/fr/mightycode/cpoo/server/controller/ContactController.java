/*package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.PublicMessageDTO;
import fr.mightycode.cpoo.server.dto.FullUserDTO;
import fr.mightycode.cpoo.server.service.ContactService;
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

import java.security.Principal;

@RestController
@RequestMapping("/user/friend")
@AllArgsConstructor
@CrossOrigin

public class ContactController {

    private final ContactService contactService;

    @PostMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE) //add a friend
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<ContactProfileDTO> addFriend(@PathVariable String userId) {
        return new ResponseEntity<>(new ContactProfileDTO(), HttpStatus.OK) ;
    }
}*/