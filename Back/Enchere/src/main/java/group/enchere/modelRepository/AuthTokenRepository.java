package group.Project.ModelRepository;


import group.Project.Model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthTokenRepository extends JpaRepository<AuthToken,Long> {
    @Query("SELECT id FROM AuthToken where token = ?1")
    Long findIdByToken(String token);
}
