package group.enchere.modelRepository;


import group.enchere.model.Enchere;
//import group.enchere.model.EnchereDetails;
import group.enchere.model.EnchereDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnchereRepository extends JpaRepository<Enchere,Integer> {
    @Query(name = "getAllNotDone",nativeQuery = true)
    public List<Enchere> getAllNotDone();

    @Query(name = "getDetails",nativeQuery = true)
    public EnchereDetails getDetails(@Param("idEnchere")int idEnchere);
}
