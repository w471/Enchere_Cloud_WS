package group.enchere.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name = "getSolde",
        query = "SELECT * from V_SOLDE_PER_CLIENT where idclient= :idPersonne",
        resultClass = ClientFinanceStatus.class
)
public class ClientFinanceStatus {
    @Id
    @Column(name = "idclient")
    int idClient;

    @Column(name = "totalincome")
    double totalIncome;

    @Column(name = "totalspent")
    double totalSpent;

    double solde;

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }
}
