package phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phonebook.components.CurrentUser;
import phonebook.entity.dto.UserRegistrationDTO;
import phonebook.services.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;
    private CurrentUser currentUser;

    @Autowired
    public RegisterController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("currentUser")
    public CurrentUser initCurrentUser() {
        return this.currentUser;
    }


    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initUser() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String getRegister() {
        if (currentUser.isLogged()) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/register";
        } else {
            this.userService.register(userRegistrationDTO);
        }
        return "redirect:/";
    }
}
