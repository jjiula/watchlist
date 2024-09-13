package com.openclassrooms.watchlist.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<Priority, String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value.trim().length()==1 && "LHM".contains(value.trim());
    }
}

// from Use custom validation annotations video and does not work