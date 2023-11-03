package fr.mightycode.cpoo.server.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import fr.mightycode.cpoo.server.service.RouterService;

public record MessageDTO(UUID msgID, String recipientID, String content, String authorID, String authorAddress,
                         LocalDateTime date, boolean edited){

}
