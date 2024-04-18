package dsd.cohort.application.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    @Query(value = "SELECT r FROM RecipeEntity r WHERE r.name LIKE '%:name%'")
    List<RecipeEntity> findByName(String name);

    @Query(value = "SELECT r FROM RecipeEntity r WHERE r.recipeId=:recipeId")
    RecipeEntity findByRecipeId(@Param("recipeId") String recipeId);
}
