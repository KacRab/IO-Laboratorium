package org.example.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.service.KsiazkaService;
import org.example.service.KsiegarniaService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class KsiegarniaRest {

    private final KsiegarniaService ksiegarniaService;
    private final KsiazkaService ksiazkaService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final KsiegarniaValidator validator;

//    @InitBinder
//    void initBinder(WebDataBinder binder) {binder.addValidators(validator);}

    @GetMapping("/ksiegarnie")
    List<Ksiegarnia> getKsiegarnie(
            @RequestParam(value = "phrase", required = false) String phrase,
            @RequestHeader(value = "custom-header", required = false) String customHeader,
            @CookieValue(value = "some-cookie", required = false) String someCookie
    ) {
        log.info("about to retrieve ksiegarnie list");
        log.info("phrase param: {}", phrase);
        log.info("custom-header param: {}", customHeader);
        log.info("some-cookie value: {}", someCookie);

        if(phrase!=null && phrase.equals("foo")){
            throw new IllegalArgumentException("foo");
        }

        List<Ksiegarnia> ksiegarnie = ksiegarniaService.getAllKsiegarnie();
        log.info("{} ksiegarnie found", ksiegarnie.size());
        return ksiegarnie;
    }

    @GetMapping("/ksiegarnie/{id}")
    ResponseEntity<Ksiegarnia> getKsiegarnia(@PathVariable("id") int id) {
        log.info("about to retrieve ksiegarnia {}", id);
        Ksiegarnia ksiegarnia = ksiegarniaService.getKsiegarniaById(id);
        log.info("{} ksiegarnia found", ksiegarnia);
        if(ksiegarnia != null)
            return ResponseEntity.status(200).body(ksiegarnia);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/ksiazki/{ksiazkaId}/ksiegarnie")
    ResponseEntity<List<Ksiegarnia>> getKsiegarniePosiadajaceKsiazke(@PathVariable("ksiazkaId") int ksiazkaId) {
        log.info("about to retrieve ksiegarnie posiadajace ksiazke {}", ksiazkaId);
        Ksiazka ksiazka = ksiazkaService.getKsiazkaById(ksiazkaId);
        if(ksiazka == null)
            return ResponseEntity.notFound().build();
        else {
            List<Ksiegarnia> ksiegarnie = ksiegarniaService.getKsiegarnieByKsiazka(ksiazka);
            log.info("there's {} ksiegarnie posiadajace ksiazke {}", ksiegarnie.size(), ksiazka.getTitle());
            return ResponseEntity.ok(ksiegarnie);
        }
    }

    @PostMapping("/ksiegarnie")
    ResponseEntity<?> addKsiegarnia(@Validated @RequestBody Ksiegarnia ksiegarnia, Errors errors, HttpServletRequest request) {
        log.info("about to add new ksiegarnia {}", ksiegarnia);

        if(errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = errors.getAllErrors().stream()
                    .map(oe->messageSource.getMessage(oe.getCode(), new Object[0], locale))
                    .reduce("errors:\n", (accu, oe)->accu+oe + "\n");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);
        log.info("authentication name: {}", authentication.getName());

        ksiegarnia = ksiegarniaService.addKsiegarnia(ksiegarnia);
        log.info("new ksiegarnia added {}", ksiegarnia);
        return ResponseEntity.status(HttpStatus.CREATED).body(ksiegarnia);
    }
}
