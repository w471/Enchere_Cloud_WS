package group.Project.Model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Entity
@Table(name = "authtoken")
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pk_token")
    @SequenceGenerator(name = "pk_token",sequenceName = "s_auth_token_id",allocationSize = 1)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @Column(name = "dateexpiration")
    Date dateExpiration;

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void generateToken(String identifiant, String mdp){
        Algorithm algorithm = Algorithm.HMAC256("cle_secrete".getBytes());

        try {
            this.dateExpiration  = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2023");
            this.token = JWT.create().withExpiresAt(dateExpiration).withClaim("identifiant",identifiant).withClaim("mdp",mdp).sign(algorithm);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", dateExpiration=" + dateExpiration +
                '}';
    }
}
