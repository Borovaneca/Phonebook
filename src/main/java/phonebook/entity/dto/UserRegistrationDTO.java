package phonebook.entity.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SessionScope
@Component("userRegistrationDTO")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRegistrationDTO {

    @Size(min = 4, max = 20, message = "Username should be at least 4 symbols long and maximum 20!")
    @NotBlank(message = "Username can't be empty or whitespace!")
    private String username;
    @Email(message = "Should be a valid Email address!")
    private String email;
    @Size(min = 5, max = 20, message = "Password should be at least 5 symbols.")
    private String password;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
