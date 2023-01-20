package group.enchere.model;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
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
public class EnchereStatus extends EnchereSuperClass{
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EnchereStatus{" +
                "status=" + status +
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
