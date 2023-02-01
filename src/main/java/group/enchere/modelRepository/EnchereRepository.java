package group.enchere.modelRepository;


import group.enchere.model.*;
//import group.enchere.model.EnchereDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface EnchereRepository extends JpaRepository<Enchere,Integer> {
    @Query(name = "getAllNotDone",nativeQuery = true)
    public List<Enchere> getAllNotDone();

    @Query(name = "getDetails",nativeQuery = true)
    public EnchereDetails getDetails(@Param("idEnchere")int idEnchere);

    @Query(name = "getHistorique",nativeQuery = true)
    public List<HistoriqueEnchere> getHistorique(@Param("idEnchere") int idEnchere);

    @Query(name = "allPossesed",nativeQuery = true)
    public List<EnchereStatus> allPossesed(@Param("idPersonne") int idPersonne);

    @Query(name = "getInvolved",nativeQuery = true)
    public List<EnchereStatus> getInvolved(@Param("idPersonne") int idPersonne);

    @Query(name = "whereIdCategorieNull",nativeQuery = true)
    public List<Categorie> whereIdCategorieNull();

}
