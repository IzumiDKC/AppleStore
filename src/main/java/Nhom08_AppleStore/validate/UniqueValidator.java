package Nhom08_AppleStore.validate;

import Nhom08_AppleStore.model.User;
import Nhom08_AppleStore.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final UserRepository userRepository;

    public UniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void initialize(Unique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        final String PHONE_PATTERN = "[0][0-9]*$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(s);
        User existingUser;
        if (matcher.matches())
            existingUser = userRepository.findByPhone(s);
        else if (s.contains("@")) {
            existingUser = userRepository.findByEmail(s);
        } else
            existingUser = userRepository.findByUsername(s);

        if (existingUser != null)
            return false;
        return true;
    }
}

