package org.example.web.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Autor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.service.KsiazkaService;
import org.example.service.KsiegarniaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KsiazkaController {

    private final KsiegarniaService ksiegarniaService;
    private final KsiazkaService ksiazkaService;

    @GetMapping("/ksiazki")  // /ksiazki?ksiegarniaId=1
    String getKsiazki(
            Model model,
            @RequestParam(value="ksiegarniaId", required = false) Integer ksiegarniaId,
            @RequestParam(value = "autorId", required = false) Integer autorId){
        log.info("about to display ksiazki list in ksiegarnia {}", ksiegarniaId);
        if(ksiegarniaId!=null) {
            Ksiegarnia ksiegarnia = ksiegarniaService.getKsiegarniaById(ksiegarniaId);
            List<Ksiazka> ksiazki = ksiegarniaService.getKsiazkiInKsiegarnia(ksiegarnia);
            model.addAttribute("ksiazki", ksiazki);
            model.addAttribute("title", "Ksiazki in ksiegarnia '"+ksiegarnia.getName()+"'");
        } else if (autorId != null){
            Autor autor = ksiazkaService.getAutorById(autorId);
            List<Ksiazka> ksiazki = ksiazkaService.getKsiazkiByAutor(autor);
            model.addAttribute("ksiazki", ksiazki);
            model.addAttribute("title", "Ksiazki written by '"+autor.getLastName()+"'");
        } else {
            List<Ksiazka> ksiazki = ksiazkaService.getAllKsiazki();
            model.addAttribute("ksiazki", ksiazki);
            model.addAttribute("title", "Ksiazki");
        }
        return "ksiazkiView";
    }
}
