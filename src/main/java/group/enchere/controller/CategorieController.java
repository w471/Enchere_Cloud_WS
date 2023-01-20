package group.enchere.controller;

import group.enchere.model.Categorie;
import group.enchere.model.CategorieStat;
import group.enchere.modelRepository.CategorieRepository;
import group.enchere.modelRepository.CategorieStatRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategorieController {
    CategorieStatRepository ctRepo;
    CategorieRepository repoCatego;

    public CategorieController(CategorieStatRepository repo1,CategorieRepository repo2){
        ctRepo =  repo1;
        repoCatego = repo2;
    }

    @GetMapping("/stat1")
    public List<CategorieStat> getStatPourcentage() {
        return ctRepo.getPourcentage();
    }

    @GetMapping("/stat2")
    public List<CategorieStat> getStatTrend() {
        return ctRepo.getLongestDiscussion();
    }

    @GetMapping("/stat3")
    public List<CategorieStat> getStatHighScore() {
        return ctRepo.getHighestDiscussion();
    }

    @GetMapping
    public List<Categorie> getAllCategorie(){
        return repoCatego.findAll();
    }
}
