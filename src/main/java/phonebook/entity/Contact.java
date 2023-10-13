package phonebook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phonebook.entity.enums.ContactType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "contats")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String number;
    @Column
    private String email;
    @Enumerated(EnumType.STRING)
    private ContactType contactType;
    @Column
    private String city;
    @Column
    private LocalDate addedOn = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    @ManyToOne
    private User user;

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public Contact setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Contact setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public Contact setContactType(ContactType contactType) {
        this.contactType = contactType;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Contact setCity(String city) {
        this.city = city;
        return this;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public Contact setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Contact setUser(User user) {
        this.user = user;
        return this;
    }
}
