package phonebook.components;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import phonebook.entity.Contact;

import java.util.List;

@SessionScope
@Component("currentUser")
public class CurrentUser {

    private String username;
    private boolean isLogged;
    private List<Contact> contacts;

    public CurrentUser() {
    }

    public CurrentUser(String username, boolean isLogged, List<Contact> contacts) {
        this.username = username;
        this.isLogged = isLogged;
        this.contacts = contacts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public boolean isGuest() {
        return !this.isLogged;
    }
}
