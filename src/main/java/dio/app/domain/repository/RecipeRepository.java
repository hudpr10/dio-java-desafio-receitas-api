package dio.app.domain.repository;

import dio.app.domain.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    boolean existsBySlug(String slug);
}
