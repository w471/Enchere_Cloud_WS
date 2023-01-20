package group.enchere.modelRepository;

import group.enchere.model.Client;
import group.enchere.model.ClientFinanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query(name = "getSolde",nativeQuery = true)
    public ClientFinanceStatus getSolde(@Param("idPersonne") int idPersonne);
}
