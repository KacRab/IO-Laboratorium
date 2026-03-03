package org.example.service;

import org.example.model.Ksiegarnia;
import org.example.repository.KsiazkaDao;
import org.example.repository.KsiegarniaDao;
import org.example.repository.mem.MemKsiazkaDao;
import org.example.repository.mem.MemKsiegarniaDao;
import org.example.service.impl.KsiegarniaServiceBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class KsiazkiServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find ksiegarnie!");

        ApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        KsiegarniaService service = context.getBean(KsiegarniaService.class);
        KsiegarniaService service2 = context.getBean(KsiegarniaService.class);

        List<Ksiegarnia> ksiegarnie = service.getAllKsiegarnie();
        System.out.println(ksiegarnie.size() + " ksiegarnie found:");
        ksiegarnie.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string: " + foo);
    }
}
