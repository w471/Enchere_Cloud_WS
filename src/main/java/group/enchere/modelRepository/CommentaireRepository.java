package group.enchere.modelRepository;
import group.enchere.ModelMongo.Commentaire;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentaireRepository extends MongoRepository<Commentaire,Integer> {
}
