package org.example.web.rest;

import lombok.RequiredArgsConstructor;
import org.example.model.Ksiegarnia;
import org.example.service.KsiegarniaService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class KsiegarniaValidator implements Validator {

    private final KsiegarniaService ksiegarniaService;

    @Override
    public boolean supports(Class<?> clazz) {return clazz.isAssignableFrom(Ksiegarnia.class);}

    @Override
    public void validate(Object target, Errors errors){
        Ksiegarnia validateKsiegarnia = (Ksiegarnia) target;

        boolean duplicated = ksiegarniaService.getAllKsiegarnie().stream()
                .anyMatch(ksiegarnia->ksiegarnia.getName().equalsIgnoreCase(validateKsiegarnia.getName()));
        if(duplicated){
            errors.rejectValue("name", "ksiegarnia.name.duplicated");
        }
    }
}
