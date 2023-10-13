package phonebook.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import phonebook.components.CurrentUser;
import phonebook.entity.Contact;
import phonebook.entity.dto.ContactDTO;
import phonebook.repositories.ContactRepository;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ContactService(ContactRepository contactRepository, ModelMapper modelMapper, UserService userService) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    public void addContact(ContactDTO contactDTO) {
        Contact contact = this.modelMapper.map(contactDTO, Contact.class);
        this.userService.addContact(contact);
    }

    public void deleteContact(String name) {
        this.userService.deleteContact(this.contactRepository.findByName(name).orElseThrow());
    }

    public void edit(Contact contact) {
            this.contactRepository.save(contact);
    }

    public Contact getByName(String name) {
        return this.contactRepository.findByName(name).orElseThrow();
    }

    private void editContact(ContactDTO contactDTO, Contact contact) {
        contact.setContactType(contactDTO.getType()).
                setCity(contactDTO.getCity()).
                setName(contactDTO.getName()).
                setEmail(contactDTO.getEmail()).
                setNumber(contactDTO.getNumber());
        this.contactRepository.save(contact);
    }

    public Contact merge(Contact editingContact, ContactDTO contactDTO) {
        return editingContact.setName(contactDTO.getName()).
                setNumber(contactDTO.getNumber()).
                setEmail(contactDTO.getEmail()).
                setContactType(contactDTO.getType()).
                setCity(contactDTO.getCity());
    }
}
