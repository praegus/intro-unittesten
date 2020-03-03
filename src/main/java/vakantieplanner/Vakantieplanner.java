package vakantieplanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
public class Vakantieplanner {

    // Entry-point voor onze Spring Boot backend-app
    public static void main(String[] args) {
        SpringApplication.run(Vakantieplanner.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("\n\nStarting Praegus Academy Vakantieplanner....");
        };
    }
}
