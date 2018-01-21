package ua.kiev.prog.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.kiev.prog.model.entity.CustomUser;

public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CustomUser user = (CustomUser) target;
        //validate something else

    }

}