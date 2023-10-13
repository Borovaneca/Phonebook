package phonebook.entity.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import phonebook.entity.User;
import phonebook.repositories.UserRepository;
import phonebook.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ValidLoginValidator implements ConstraintValidator<ValidLogin, Object> {

    private String username;
    private String password;
    private String message;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void initialize(ValidLogin constraintAnnotation) {
        this.username = constraintAnnotation.currentUsername();
        this.password = constraintAnnotation.currentPassword();
        this.message = constraintAnnotation.message();
    }

    public ValidLoginValidator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory
                .forBeanPropertyAccess(value);

        Object username = beanWrapper.getPropertyValue(this.username);
        Object password = beanWrapper.getPropertyValue(this.password);

        Optional<User> user = this.userRepository.findByUsername(username.toString());
        if (user.isPresent()) {
            return this.passwordEncoder.matches(password.toString(), user.get().getPassword());
        }
        return false;
    }
}
