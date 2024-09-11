package com.openclassrooms.watchlist.validation;


import jakarta.validation.Constraint;
import org.springframework.context.PayloadApplicationEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriorityValidator.class)
public @interface Priority {

    String message() default "Please enter L,M, or H for priority";

    Class<?>[] groups() default {};
    Class< ? extends PayloadApplicationEvent>[] payload() default {};
//    Class<? extends Payload>[] payload() default {}; from video
}
