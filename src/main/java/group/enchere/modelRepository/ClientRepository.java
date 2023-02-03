package group.enchere.modelRepository;

import group.enchere.model.Client;
import group.enchere.model.ClientFinanceStatus;
import group.enchere.model.EnchereStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query(name = "getSolde",nativeQuery = true)
    public ClientFinanceStatus getSolde(@Param("idPersonne") int idPersonne);

    @Query(name = "getBiddable",nativeQuery = true)
    public List<EnchereStatus> getBiddable(@Param("idPersonne") int idPersonne);
}
