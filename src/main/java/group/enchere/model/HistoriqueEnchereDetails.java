package group.enchere.model;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name = "getHistoriqueDetails",
        query = "SELECT * FROM V_HISTORIQUE_ENCHERE_DETAILS where idenchere= :idEnchere order by price desc",
        resultClass = HistoriqueEnchereDetails.class
)
public class HistoriqueEnchereDetails extends HistoriqueEnchereSuperClass{
    String nom;
    String prenom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "HistoriqueEnchereDetails{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", idHistorique=" + idHistorique +
                ", idEnchere=" + idEnchere +
                ", idClient=" + idClient +
                ", price=" + price +
                '}';
    }
}
