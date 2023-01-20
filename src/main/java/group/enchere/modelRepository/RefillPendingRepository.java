package group.enchere.modelRepository;

import group.enchere.model.RefillPending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefillPendingRepository extends JpaRepository<RefillPending,Integer> {
    @Query(name = "findAllNotAccepted",nativeQuery = true)
    public List<RefillPending> findAllNotAccepted();
}
