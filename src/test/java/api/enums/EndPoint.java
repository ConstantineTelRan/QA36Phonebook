package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EndPoint {
    ADD_NEW_CONTACT("/api/contact"),
    GET_LIST_OF_CONTACTS_BY_USER("/api/contact"),
    DELETE_CONTACT("/api/contact/{id}"),
    GET_CONTACT_BY_ID("/api/contact/{id}"),
    GET_LIST_OF_CONTACTS("/api/contact"),
    UPDATE_CONTACT("/api/contact");

    private final String value;
}
