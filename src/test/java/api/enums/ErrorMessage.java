package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    CONTACT_ERROR_MESSAGE("Error! This contact doesn't exist in our DB"),
    EMAIL_ERROR_MESSAGE("Error! This email doesn't exist in our DB");

    private final String value;
}
