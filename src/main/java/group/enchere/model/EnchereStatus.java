package group.enchere.model;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import java.time.LocalDateTime;

@Entity
@NamedNativeQuery(
        name = "allPossesed",
        query = "SELECT * FROM V_ENCHERE_STATUS where idlauncher= :idPersonne",
        resultClass = EnchereStatus.class
)
@NamedNativeQuery(
        name = "getInvolved",
        query = "select * from v_enchere_status where idenchere in ( select idenchere from historique_enchere where idclient= :idPersonne)",
        resultClass = EnchereStatus.class
)
// Classe de simlpification pour recevoir le status et le prix gagnant en même temps
public class EnchereStatus extends EnchereSuperClass{
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EnchereStatus(){}

    public EnchereStatus(int idEnchere, LocalDateTime timingStart, int idLauncher, String description, int idCategorie, double startPrice, int duration, double commission, int status, double price) {
        super(idEnchere, timingStart, idLauncher, description, idCategorie, startPrice, duration, commission);
        this.status = status;
        this.price = price;
    }

    public EnchereStatus(int idEnchere, LocalDateTime timingStart, int idLauncher, String description, int idCategorie, double startPrice, int duration, double commission, int status, double price,String image) {
        super(idEnchere, timingStart, idLauncher, description, idCategorie, startPrice, duration, commission);
        this.status = status;
        this.price = price;
        this.image = image;
    }

    @Override
    public String toString() {
        return "EnchereStatus{" +
                "status=" + status +
                ", price=" + price +
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
