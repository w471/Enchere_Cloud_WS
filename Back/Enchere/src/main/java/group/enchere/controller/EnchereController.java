package group.enchere.controller;


import group.enchere.ModelUtils.Research;
import group.enchere.model.Enchere;
//import group.enchere.model.EnchereDetails;
import group.enchere.model.EnchereDetails;
import group.enchere.modelRepository.EnchereRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/encheres")
public class EnchereController {
    EnchereRepository repository;

    public EnchereController(EnchereRepository repo){
        this.repository = repo;
    }

    @GetMapping
    public List<Enchere> getAllNotDone(){
        return repository.getAllNotDone();
    }

    @GetMapping("/{idEnchere}")
    public EnchereDetails getDetails(@PathVariable int idEnchere){
        return repository.getDetails(idEnchere);
    }

    @PostMapping("/search")
    public void search(@RequestBody Research research){
        System.out.println(research);
    }

}
