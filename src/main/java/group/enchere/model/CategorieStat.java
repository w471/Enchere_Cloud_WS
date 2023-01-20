package group.enchere.model;



import javax.persistence.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity

@NamedNativeQuery(
        name = "getLongestDiscussion",
        query = "select * from V_CATEGORIE_LONGEST_DISCUSSION",
        resultClass = CategorieStat.class
)


@NamedNativeQuery(
        name = "getHghestDiscussion",
        query = "select * from V_CATEGORIE_HIGHEST_DISCUSSION",
        resultClass = CategorieStat.class
)
@NamedNativeQuery(
        name = "getPourcentage",
        query = "select * from V_CATEGORIE_POURCENTAGE",
        resultClass = CategorieStat.class
)
public class CategorieStat extends CategorieSuperClass{
    double stat;

    public double getStat() {
        return stat;
    }

    public void setStat(double stat) {
        this.stat = stat;
    }


    public CategorieStat(int idCategorie, String nomCategorie, double stat) {
        super(idCategorie, nomCategorie);
        this.stat = stat;
    }

    public CategorieStat(double stat) {
        this.stat = stat;
    }

    public CategorieStat(){}

    @Override
    public String toString() {
        return "CategorieStat{" +
                "stat=" + stat +
                ", idCategorie=" + idCategorie +
                ", nomCategorie='" + nomCategorie + '\'' +
                '}';
    }
}
