package group.enchere.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IdentifierSuperClass {
    String email;
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "IdentifierSuperClass{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
