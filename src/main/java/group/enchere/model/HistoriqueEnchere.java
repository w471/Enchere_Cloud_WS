package group.enchere.model;

import javax.persistence.*;

@Entity
@Table(name = "historique_enchere")
@NamedNativeQuery(
        name = "getHistorique",
        query = "SELECT * FROM historique_enchere where idenchere= :idEnchere order by price desc",
        resultClass = HistoriqueEnchere.class
)
public class HistoriqueEnchere {
    @Id
    @Column(name = "idhistorique")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_historique_enchere")
    @SequenceGenerator(name = "pk_historique_enchere",sequenceName = "s_historique_enchere_id",allocationSize = 1)
    int idHistorique;

    @Column(name = "idenchere")
    int idEnchere;
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
