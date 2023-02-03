package group.enchere.modelRepository;

import group.enchere.model.HistoriqueEnchere;
import group.enchere.model.HistoriqueEnchereDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoriqueEnchereRepository extends JpaRepository<HistoriqueEnchere,Integer> {
    @Query(name = "getHistoriqueDetails",nativeQuery = true)
    public List<HistoriqueEnchereDetails> getHistoriqueDetails(@Param("idEnchere") int idEnchere);
}
