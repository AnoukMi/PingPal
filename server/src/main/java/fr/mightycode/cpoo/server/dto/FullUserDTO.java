package fr.mightycode.cpoo.server.dto;

public record FullUserDTO(String login, String password, boolean remember, int icon, String firstname, String lastname, String birthday, String address) {
}