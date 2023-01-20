package group.enchere.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedNativeQuery(
        name="getAllNotDone",
        query = "SELECT * FROM v_enchere_not_done"
        ,resultClass = Enchere.class)

@NamedNativeQuery(
        name ="search",
        query = "SELECT * FROM enchere",
        resultClass = Enchere.class
)
@NamedNativeQuery(
        name = "whereIdCategorieNull",
        query = "SELECT * FROM enchere where idcategorie=0",
        resultClass = Categorie.class
)
public class Enchere extends EnchereSuperClass{

}
