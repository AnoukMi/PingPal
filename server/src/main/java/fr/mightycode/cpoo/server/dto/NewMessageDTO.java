package fr.mightycode.cpoo.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record NewMessageDTO(@NotEmpty @Email String to, @NotEmpty String type, @NotEmpty String body) {
}
