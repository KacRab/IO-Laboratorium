package org.example.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Ksiegarnia;
import org.example.service.KsiegarniaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class KsiegarniaRest {

    private final KsiegarniaService ksiegarniaService;

    @GetMapping("/ksiegarnie")
    List<Ksiegarnia> getKsiegarnie() {
        log.info("about to retrieve ksiegarnie list");
        List<Ksiegarnia> ksiegarnie = ksiegarniaService.getAllKsiegarnie();
        log.info("{} ksiegarnie found", ksiegarnie.size());
        return ksiegarnie;
    }
}
