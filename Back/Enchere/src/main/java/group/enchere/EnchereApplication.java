package group.enchere;

import group.enchere.ModelUtils.Research;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@SpringBootApplication
public class EnchereApplication {

    public static void main(String[] args) {
//        SpringApplication.run(EnchereApplication.class, args);
        Research research = new Research();
        research.callFunction();
    }
        //Admin admin=new
}
