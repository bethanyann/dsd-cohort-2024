package dsd.cohort.application.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    RecipeEntity findByName(String name);
}
