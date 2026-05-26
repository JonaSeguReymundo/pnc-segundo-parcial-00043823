package com.uca.pncsegundoparcialcoworking.utils.validations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueSpaceNameValidator.class)
public @interface UniqueSpaceName {
    String message() default "El nombre del espacio ya está registrado. Debe ser único.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
