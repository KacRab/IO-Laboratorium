package org.example.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(basePackages = "example.web.rest")
@RequiredArgsConstructor
@Slf4j
public class VodAdvice {

    private final KsiegarniaValidator ksiegarniaValidator;
    private final KsiazkaValidator ksiazkaValidator;

    @InitBinder("ksiegarnia")
    void initBinderForKsiegarnia(WebDataBinder binder) {binder.addValidators(ksiegarniaValidator);}

    @InitBinder("ksiazkaDTO")
    void initBinderForKsiazka(WebDataBinder binder) {binder.addValidators(ksiazkaValidator);}

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        log.error("illegal argument provided", e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }
}
