package org.example.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.service.KsiazkaService;
import org.example.service.KsiegarniaService;
import org.example.web.rest.dto.KsiazkaDTO;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class KsiazkaRest {

    private final KsiegarniaService ksiegarniaService;
    private final KsiazkaService ksiazkaService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping("/ksiazki")
    List<Ksiazka> getKsiazki() {
        log.info("about to retrieve ksiazki list");
        List<Ksiazka> ksiazki = ksiazkaService.getAllKsiazki();
        log.info("{} ksiazki found", ksiazki.size());
        return ksiazki;
    }

    @GetMapping("/ksiazki/{id}")
    ResponseEntity<Ksiazka> getKsiazka(@PathVariable("id") int id){
        log.info("about to retrieve ksiazka {}", id);
        Ksiazka ksiazka = ksiazkaService.getKsiazkaById(id);
        log.info("ksiazka found: {}", ksiazka);
        if (ksiazka != null)
            return ResponseEntity.ok(ksiazka);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/ksiegarnie/{ksiegarniaId}/ksiazki")
    ResponseEntity<List<Ksiazka>> getKsiazkiSoldAtKsiegarnia(@PathVariable("ksiegarniaId") int ksiegarniaId){
        log.info("about to retrieve ksiazki sold at ksiegarnia {}", ksiegarniaId);
        Ksiegarnia ksiegarnia = ksiegarniaService.getKsiegarniaById(ksiegarniaId);
        if(ksiegarnia == null)
            return ResponseEntity.notFound().build();
        else{
            List<Ksiazka> ksiazki = ksiegarniaService.getKsiazkiInKsiegarnia(ksiegarnia);
            log.info("there's {} ksiazki sold at ksiegarnia {}", ksiazki.size(), ksiegarnia.getName());
            return ResponseEntity.ok(ksiazki);
        }
    }

    @PostMapping("/ksiazki")
    ResponseEntity<?> addKsiazka(@RequestBody KsiazkaDTO ksiazkaDTO){
        log.info("about to add new ksiazka {}", ksiazkaDTO);
        Ksiazka ksiazka = new Ksiazka();
        ksiazka.setTitle(ksiazkaDTO.getTitle());
        ksiazka.setCover(ksiazkaDTO.getCover());
        ksiazka.setRating(ksiazkaDTO.getRating());
        ksiazka.setAutor(ksiazkaService.getAutorById(ksiazkaDTO.getAutorId()));

        ksiazka = ksiazkaService.addKsiazka(ksiazka);
        log.info("new ksiazka added: {}", ksiazka);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + ksiazka.getId())
                        .build()
                        .toUri())
                .body(ksiazka);
    }
}
