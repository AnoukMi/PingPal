package fr.mightycode.cpoo.server.dto;

import fr.mightycode.cpoo.server.model.UserData;

public record ContactProfileDTO(String userID, String peerAddress, int icon, String firstname, String lastname, String birthday, String sharedMessage) {
    public ContactProfileDTO(UserData user) {
        this(user.getLogin(),
                user.getAddress(),
                user.getIcon(),
                user.getFirstname(),
                user.getLastname(),
                user.getBirthday().toString(),
                user.getSharedMessage());
    }
}
