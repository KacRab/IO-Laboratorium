package org.example.web.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class KsiegarniaController {

    private final KsiegarniaService ksiegarniaService;
    private final KsiazkaService ksiazkaService;

    @GetMapping("/ksiegarnie")  // /ksiegarnie?ksiazkaId=1
    String getKsiegarnie(
            Model model,
            @RequestParam(value="ksiazkaId", required = false) Integer ksiazkaId){
        log.info("about to display ksiegarnie list selling ksiazka {}", ksiazkaId);
        if(ksiazkaId!=null) {
            Ksiazka ksiazka = ksiazkaService.getKsiazkaById(ksiazkaId);
            List<Ksiegarnia> ksiegarnie = ksiegarniaService.getKsiegarnieByKsiazka(ksiazka);
            model.addAttribute("ksiegarnie", ksiegarnie);
            model.addAttribute("title", "Ksiegarnie selling '"+ksiazka.getTitle()+"'");
        }else {
            List<Ksiegarnia> ksiegarnie = ksiegarniaService.getAllKsiegarnie();
            model.addAttribute("ksiegarnie", ksiegarnie);
            model.addAttribute("title", "Ksiegarnie");
        }
        return "ksiegarnieView";
    }
}
