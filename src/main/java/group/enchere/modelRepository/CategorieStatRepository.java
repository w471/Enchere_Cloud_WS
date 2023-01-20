package group.enchere.modelRepository;

import group.enchere.model.CategorieStat;

import org.springframework.cache.annotation.CacheConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

public interface CategorieStatRepository extends JpaRepository<CategorieStat,Integer> {

    @Query(name = "getHghestDiscussion",nativeQuery = true)
    public List<CategorieStat> getHighestDiscussion();

    @Query(name = "getLongestDiscussion",nativeQuery = true)
    public List<CategorieStat> getLongestDiscussion();

    @Query(name = "getPourcentage",nativeQuery = true)
    public List<CategorieStat> getPourcentage();
}
