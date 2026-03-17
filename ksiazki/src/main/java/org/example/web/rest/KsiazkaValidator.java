package org.example.web.rest;

import lombok.RequiredArgsConstructor;
import org.example.model.Autor;
import org.example.service.KsiazkaService;
import org.example.web.rest.dto.KsiazkaDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class KsiazkaValidator implements Validator {

    private final KsiazkaService ksiazkaService;

    @Override
    public boolean supports(Class<?> clazz) { return clazz.isAssignableFrom(KsiazkaDTO.class);}

    @Override
    public void validate(Object target, Errors errors) {
        KsiazkaDTO ksiazka = (KsiazkaDTO) target;
        Autor autor = ksiazkaService.getAutorById(ksiazka.getAutorId());
        if(autor == null){
            errors.rejectValue("autorId", "ksiazka.autor.missing");
        }
    }
}
