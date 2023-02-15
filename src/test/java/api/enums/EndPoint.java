package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EndPoint {
    ADD_NEW_CONTACT("/api/contact"),
    GET_LIST_OF_CONTACTS_BY_USER("/api/contact");

    private final String value;
}
