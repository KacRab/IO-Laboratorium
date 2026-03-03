package org.example;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Ksiegarnia;
import org.example.service.KsiegarniaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class KsiazkiComponent implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {

    private final KsiegarniaService ksiegarniaService;

    public KsiazkiComponent(KsiegarniaService ksiegarniaService) { this.ksiegarniaService = ksiegarniaService; }

    @PostConstruct
    void init(){ log.info("in post construct");}

    @Override
    public void run(String... args) throws Exception {
        log.info("program arguments: {}", Arrays.toString(args));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("on context refreshed");
        List<Ksiegarnia> ksiegarnie = ksiegarniaService.getAllKsiegarnie();
        log.info("{} ksiegarnie found", ksiegarnie.size());
        ksiegarnie.forEach(ksiegarnia ->log.info("{}", ksiegarnia));
    }

    @EventListener
    public void eventListener(ContextRefreshedEvent event) {log.info("on context refreshed (from annotated method");}
}
