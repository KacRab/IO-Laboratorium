package org.example.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.service.KsiazkaService;
import org.example.service.KsiegarniaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class KsiegarniaRest {

    private final KsiegarniaService ksiegarniaService;
    private final KsiazkaService ksiazkaService;

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
    ResponseEntity<Ksiegarnia> addKsiegarnia(@RequestBody Ksiegarnia ksiegarnia) {
        log.info("about to add new ksiegarnia {}", ksiegarnia);
        // TODO validation
        ksiegarnia = ksiegarniaService.addKsiegarnia(ksiegarnia);
        log.info("new ksiegarnia added {}", ksiegarnia);
        return ResponseEntity.status(HttpStatus.CREATED).body(ksiegarnia);
    }
}
