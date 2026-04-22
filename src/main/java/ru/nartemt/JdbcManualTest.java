package ru.nartemt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.nartemt.config.DatabaseConfig;
import ru.nartemt.model.entity.guitar.Guitar;
import ru.nartemt.service.GuitarService;

import java.util.List;

public class JdbcManualTest {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        System.out.println("---- Context INITIALIZED ----");
        GuitarService service = context.getBean(GuitarService.class);
        List<Guitar> guitars = service.findAll();
        guitars.forEach(i -> System.out.println(i));
        System.out.println("---- TEST PASSED ----");
    }
}
