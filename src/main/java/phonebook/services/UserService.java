package phonebook.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import phonebook.components.CurrentUser;
import phonebook.entity.Contact;
import phonebook.entity.User;
import phonebook.entity.dto.UserLoginDTO;
import phonebook.entity.dto.UserRegistrationDTO;
import phonebook.exceptions.PasswordException;
import phonebook.repositories.ContactRepository;
import phonebook.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ContactRepository contactRepository;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.contactRepository = contactRepository;
    }

    public void addContact(Contact contact) {
        User user = this.userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
        user.getContacts().add(contact);
        contact.setUser(user);
        this.userRepository.save(user);
        this.contactRepository.save(contact);
    }


    public void register(UserRegistrationDTO userRegistrationDTO) {
        User user = this.modelMapper.map(userRegistrationDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(userRegistrationDTO.getPassword()));
        this.userRepository.save(user);
        loginCurrentUser(user);
    }

    public void login(UserLoginDTO userLoginDTO) {
        this.userRepository.findByUsername(userLoginDTO.getUsername()).ifPresent(this::loginCurrentUser);
    }

    private void loginCurrentUser(User user) {
        currentUser.setUsername(user.getUsername());
        currentUser.setLogged(true);
        currentUser.setContacts(user.getContacts());
    }

    private void logoutCurrentUser() {
        currentUser.setUsername(null);
        currentUser.setLogged(false);
    }

    public void deleteContact(Contact contact) {
        User user = this.userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            user.getContacts().remove(contact);
        this.userRepository.save(user);
        this.contactRepository.delete(contact);
    }

    public List<Contact> getContacts(String username) {
        return this.userRepository.findByUsername(username).get().getContacts();
    }

    public void logout() {
        this.logoutCurrentUser();
    }
}
