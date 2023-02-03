package group.enchere.controller;


import group.enchere.ModelUtils.Research;
import group.enchere.model.Enchere;

import group.enchere.model.EnchereStatus;
import group.enchere.modelRepository.EnchereRepository;
import group.enchere.modelRepository.HistoriqueEnchereRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/encheres")
public class EnchereController {
    EnchereRepository repository;
    HistoriqueEnchereRepository hRepo;
    JdbcTemplate jdbcTemplate;

    public EnchereController(EnchereRepository repo,JdbcTemplate temp,HistoriqueEnchereRepository repo2){
        this.repository = repo;
        jdbcTemplate = temp;
        hRepo = repo2;
    }


//    @GetMapping
//    public List<Enchere> getAllNotDone(){
////        before making any select , check if any of the
//        return repository.getAllNotDone();
//    }

    @GetMapping("/{idEnchere}")
    public Object[] getDetails(@PathVariable int idEnchere){
        Object[] data = new Object[3];
        data[0] = repository.getDetails(idEnchere);
        data[1] = hRepo.getHistoriqueDetails(idEnchere);
        data[2] = repository.findEnchereStatusFromEnchere(idEnchere).getPrice();
        return data;
    }

    @PutMapping("/{idEnchere}")
    public void update(@PathVariable int idEnchere,@RequestParam int duration){
        // it's quite tricky to actually pass the id of the object from the form as a data since it's behaving in a way where it's gonna auto increment
        //  therefore we need to use another way

        Optional<Enchere> newEnchere = repository.findById(idEnchere);
        Enchere enchere2 = newEnchere.orElse(null);
        enchere2.setDuration(duration);
        repository.save(enchere2);
//        System.out.println("valeurs duration: "+ duration);
//        System.out.println(newEnchere);
    }


    @PostMapping("/search")
    public ArrayList<EnchereStatus> search(@RequestBody Research research){
        return research.executeSearch(research.prepareResearch(),jdbcTemplate);
    }

    @PostMapping
    public void add(@RequestBody Enchere enchere){
        repository.save(enchere);
    }

}
