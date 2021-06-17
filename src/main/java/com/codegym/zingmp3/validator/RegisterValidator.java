package com.codegym.zingmp3.validator;

import com.codegym.zingmp3.model.User;
import com.codegym.zingmp3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "label.password.confirm");
        }

        if (userService.existByEmail(user.getEmail())) {
            errors.rejectValue("email", "label.email.exist");
        }
    }
}
