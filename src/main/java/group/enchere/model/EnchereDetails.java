package group.enchere.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name="getDetails",
        query = "SELECT * FROM V_ENCHERE_DETAILS where idenchere= :idEnchere"
        ,resultClass = EnchereDetails.class)
public class EnchereDetails extends EnchereSuperClass {
    String nom;
    String prenom;
    @Column(name = "nomcategorie")
    String nomCategorie;

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

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    @Override
    public String toString() {
        return "EnchereDetails{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nomCategorie='" + nomCategorie + '\'' +
                ", idEnchere=" + idEnchere +
                ", timingStart=" + timingStart +
                ", idLauncher=" + idLauncher +
                ", description='" + description + '\'' +
                ", idCategorie=" + idCategorie +
                ", startPrice=" + startPrice +
                ", duration=" + duration +
                ", commission=" + commission +
                '}';
    }
}
