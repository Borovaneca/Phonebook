package phonebook.entity.dto;

import phonebook.entity.enums.ContactType;
import phonebook.entity.validation.UniqueContactName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContactDTO {

    @NotEmpty
    @UniqueContactName
    private String name;
    private String number;
    @Email
    private String email;
    @NotNull
    private ContactType type;
    @NotEmpty
    private String city;
    private LocalDate addedOn = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public ContactDTO() {
    }


    public String getName() {
        return name;
    }

    public ContactDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public ContactDTO setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactType getType() {
        return type;
    }

    public ContactDTO setType(ContactType type) {
        this.type = type;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ContactDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public ContactDTO setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
