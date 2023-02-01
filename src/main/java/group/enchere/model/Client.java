package group.enchere.model;

import javax.persistence.*;

@Entity
public class Client extends IdentifierSuperClass{
    @Id
    @Column(name = "idclient")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_client")
    @SequenceGenerator(name = "pk_client",sequenceName = "s_client_id",allocationSize = 1)
    int idAdmin;

    String nom;
    String prenom;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

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
        return "Client{" +
                "idAdmin=" + idAdmin +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
