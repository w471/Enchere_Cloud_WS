package group.enchere.model;

import javax.persistence.*;

@MappedSuperclass
public class CategorieSuperClass {
    @Id
    @Column(name = "idcategorie")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_categorie")
    @SequenceGenerator(name = "pk_categorie",sequenceName = "s_categorie_id",allocationSize = 1)
    int idCategorie;

    @Column(name = "nomcategorie")
    String nomCategorie;

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", nomCategorie='" + nomCategorie + '\'' +
                '}';
    }

    public CategorieSuperClass(int idCategorie, String nomCategorie) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
    }

    public CategorieSuperClass(){}
}
