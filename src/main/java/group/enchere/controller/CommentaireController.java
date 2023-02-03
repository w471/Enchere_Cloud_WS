package group.enchere.controller;

import group.enchere.ModelMongo.Commentaire;
import group.enchere.modelRepository.CommentaireRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/commentaires")
public class CommentaireController {
    CommentaireRepository repoComment;

    public CommentaireController(CommentaireRepository repo1){
        repoComment = repo1;
    }

    @PostMapping
    public void comment(@RequestBody Commentaire commentaire){
        repoComment.save(commentaire);
    }

    @GetMapping
    public List<Commentaire> getAll(){
        return repoComment.findAll();
    }
}
