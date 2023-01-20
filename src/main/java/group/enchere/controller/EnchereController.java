package group.enchere.controller;


import group.enchere.ModelUtils.Research;
import group.enchere.model.Enchere;
//import group.enchere.model.EnchereDetails;
import group.enchere.model.EnchereDetails;
import group.enchere.modelRepository.EnchereRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/encheres")
public class EnchereController {
    EnchereRepository repository;

    public EnchereController(EnchereRepository repo){
        this.repository = repo;
    }


//    @GetMapping
//    public List<Enchere> getAllNotDone(){
////        before making any select , check if any of the
//        return repository.getAllNotDone();
//    }

    @GetMapping("/{idEnchere}")
    public Object[] getDetails(@PathVariable int idEnchere){
        Object[] data = new Object[2];
        data[0] = repository.getDetails(idEnchere);
        data[1] = repository.getHistorique(idEnchere);
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
        System.out.println("valeurs duration: "+ duration);
        System.out.println(newEnchere);
    }


    @PostMapping("/search")
    public void search(@RequestBody Research research){
        Object[] param = new Object[2];
        param[0] = 1;
        param[1] = 2000;
        Object[][] bref =  research.prepareResearch();
        List<Enchere> result = repository.search(bref[0][0], bref[1][0], bref[0][1], bref[1][1], bref[0][2], bref[1][2]);
        System.out.println(result);
    }

    @PostMapping
    public void add(@RequestBody Enchere enchere){
        repository.save(enchere);
    }

}
