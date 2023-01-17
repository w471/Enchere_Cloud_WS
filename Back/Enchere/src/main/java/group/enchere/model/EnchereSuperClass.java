package group.enchere.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class EnchereSuperClass {
    @Id
    @Column(name = "idenchere")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_administrateur")
    @SequenceGenerator(name = "pk_administrateur",sequenceName = "s_enchere_id",allocationSize = 1)
    int idEnchere;

    @Column(name = "timingstart")
    LocalDateTime timingStart;

    @Column(name = "idlauncher")
    int idLauncher;

    String description;

    @Column(name = "idcategorie")
    int idCategorie;

    @Column(name = "startprice")
    double startPrice;

    int duration;

    double commission;

    public int getIdEnchere() {
        return idEnchere;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    public LocalDateTime getTimingStart() {
        return timingStart;
    }

    public void setTimingStart(LocalDateTime timingStart) {
        this.timingStart = timingStart;
    }

    public int getIdLauncher() {
        return idLauncher;
    }

    public void setIdLauncher(int idLauncher) {
        this.idLauncher = idLauncher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "idEnchere=" + idEnchere +
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
