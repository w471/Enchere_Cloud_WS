package group.enchere.model;

import javax.persistence.*;

@MappedSuperclass
public class HistoriqueEnchereSuperClass {
    @Id
    @Column(name = "idhistorique")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_historique_enchere")
    @SequenceGenerator(name = "pk_historique_enchere",sequenceName = "s_historique_enchere_id",allocationSize = 1)
    int idHistorique;

    @Column(name = "idenchere")
    int idEnchere;
    @Column(name = "idclient")
    int idClient;
    double price;

    public int getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }

    public int getIdEnchere() {
        return idEnchere;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "HistoriqueEnchere{" +
                "idHistorique=" + idHistorique +
                ", idEnchere=" + idEnchere +
                ", idClient=" + idClient +
                ", price=" + price +
                '}';
    }
}
