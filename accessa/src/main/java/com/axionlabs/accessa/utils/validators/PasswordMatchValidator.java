package com.axionlabs.accessa.utils.validators;

import com.axionlabs.accessa.dto.user.request.RegisterRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatches, RegisterRequestDto> {


    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param dto  object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(RegisterRequestDto dto, ConstraintValidatorContext context) {
        boolean isValid =  dto.getPassword().equals(dto.getConfirmPassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
