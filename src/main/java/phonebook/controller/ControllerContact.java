package phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phonebook.components.CurrentUser;
import phonebook.entity.Contact;
import phonebook.entity.dto.ContactDTO;
import phonebook.entity.dto.UserLoginDTO;
import phonebook.exceptions.PasswordException;
import phonebook.services.ContactService;
import phonebook.services.UserService;

import javax.validation.Valid;

@Controller
public class ControllerContact {

    private UserService userService;
    private ContactService contactService;
    private CurrentUser currentUser;
    private Contact editingContact;

    @Autowired
    public ControllerContact(UserService userService, ContactService contactService, CurrentUser currentUser) {
        this.userService = userService;
        this.contactService = contactService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("currentUser")
    public CurrentUser init() {
        return new CurrentUser();
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initLogin() {
        return new UserLoginDTO();
    }

    @ModelAttribute("contactDTO")
    public ContactDTO initContact() {
        return new ContactDTO();
    }

    @GetMapping("/")
    public String home() {
        if (currentUser.isLogged()) {
            return "redirect:/contacts";
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }

    @PostMapping("/")
    public String login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
                return "redirect:/";
            }
            this.userService.login(userLoginDTO);
            return "redirect:/";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("currentUser", currentUser);
        if (!currentUser.isLogged()) {
            return "index";
        }
        return "contacts";
    }

    @PostMapping("/contacts")
    public String add(@Valid ContactDTO contactDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("contactDTO", contactDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactDTO", bindingResult);
            return "redirect:/contacts";
        }
        this.contactService.addContact(contactDTO);
        this.currentUser.setContacts(this.userService.getContacts(currentUser.getUsername()));
        return "redirect:/contacts";
    }

    @GetMapping("/edit{name}")
    public String editForm(@PathVariable String name, Model model) {
        editingContact = this.contactService.getByName(name);
        model.addAttribute("current", editingContact);
        return "edit";
    }

    @PutMapping("/edit{name}")
    public String edit(ContactDTO contactDTO) {
        editingContact = this.contactService.merge(editingContact, contactDTO);
        this.contactService.edit(editingContact);
        this.currentUser.setContacts(this.userService.getContacts(currentUser.getUsername()));
        return "redirect:/contacts";
    }

    @DeleteMapping("/delete{name}")
    public String delete(@PathVariable String name) {
        this.contactService.deleteContact(name);
        this.currentUser.setContacts(this.userService.getContacts(currentUser.getUsername()));
        return "redirect:/";
    }
}
