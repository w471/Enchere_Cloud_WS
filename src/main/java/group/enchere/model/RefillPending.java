package group.enchere.model;

import javax.persistence.*;

@Entity
@Table(name = "refill_pending")
@NamedNativeQuery(
        name = "findAllNotAccepted",
        query = "SELECT * FROM REFILL_PENDING WHERE VALIDE=0",
        resultClass = RefillPending.class
)
public class RefillPending {
    @Id
    @Column(name = "idrefill")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_refill")
    @SequenceGenerator(name = "pk_refill",sequenceName = "s_refill_pending_id",allocationSize = 1)
    int idRefill;

    @Column(name = "idclient")
    int idClient;

    @Column(name = "vola")
    double vola;

    int valide;

    public int getIdRefill() {
        return idRefill;
    }

    public void setIdRefill(int idRefill) {
        this.idRefill = idRefill;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {this.idClient = idClient;}

    public double getVola() {
        return vola;
    }

    public void setVola(double vola) throws Exception {
        if(vola<0)
            throw new Exception("Pas d'argent negatif");

        this.vola = vola;
    }

    public int getValide() {
        return valide;
    }

    public void setValide(int valide) {
        this.valide = valide;
    }

    @Override
    public String toString() {
        return "RefillPending{" +
                "idRefill=" + idRefill +
                ", idClient=" + idClient +
                ", vola=" + vola +
                ", valide=" + valide +
                '}';
    }
}
