package group.enchere.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@NamedNativeQuery(
        name="getAllNotDone",
        query = "SELECT * FROM v_enchere_not_done"
        ,resultClass = Enchere.class)
@NamedNativeQuery(
        name = "whereIdCategorieNull",
        query = "SELECT * FROM enchere where idcategorie=0",
        resultClass = Categorie.class
)
public class Enchere extends EnchereSuperClass{
    public Enchere(){}

    public Enchere(int idEnchere, LocalDateTime timingStart, int idLauncher, String description, int idCategorie, double startPrice, int duration, double commission) {
        super(idEnchere, timingStart, idLauncher, description, idCategorie, startPrice, duration, commission);
    }
}
