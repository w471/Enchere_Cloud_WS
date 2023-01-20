package group.enchere.model;

import javax.persistence.*;

@Entity
public class Admin extends IdentifierSuperClass{
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }


    @Id
    @Column(name = "idadmin")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_admin")
    @SequenceGenerator(name = "pk_admin",sequenceName = "s_admin_id",allocationSize = 1)
    int idAdmin;

}
