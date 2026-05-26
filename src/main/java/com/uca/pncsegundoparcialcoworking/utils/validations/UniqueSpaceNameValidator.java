package com.uca.pncsegundoparcialcoworking.utils.validations;

import com.uca.pncsegundoparcialcoworking.repository.SpaceRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueSpaceNameValidator implements ConstraintValidator<UniqueSpaceName, String> {

    private final SpaceRepository spaceRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.trim().isEmpty()) {
            return true;
        }
        // Validando contra la base de datos ignorando mayúsculas/minúsculas(Para que no hayan problemas luego)
        return !spaceRepository.existsByNameIgnoreCase(name);
    }
}
