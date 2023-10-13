package phonebook.entity.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import phonebook.repositories.ContactRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueContactNameValidator implements ConstraintValidator<UniqueContactName, String> {

    private final ContactRepository contactRepository;

    public UniqueContactNameValidator(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.contactRepository.findByName(value).isEmpty();
    }
}
