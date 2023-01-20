package group.enchere;

import group.enchere.ModelUtils.Research;
import group.enchere.modelRepository.EnchereRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDateTime;

@SpringBootApplication
public class EnchereApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnchereApplication.class, args);
    }
}
