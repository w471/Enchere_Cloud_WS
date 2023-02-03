package group.enchere.model;

import javax.persistence.*;

@Entity
@Table(name = "historique_enchere")
@NamedNativeQuery(
        name = "getHistorique",
        query = "SELECT * FROM historique_enchere where idenchere= :idEnchere order by price desc",
        resultClass = HistoriqueEnchere.class
)
public class HistoriqueEnchere extends HistoriqueEnchereSuperClass{

}
