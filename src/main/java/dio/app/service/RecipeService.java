package dio.app.service;

import dio.app.domain.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();
    Recipe findById(Long id);
    Recipe create(Recipe recipe);
    Recipe update(Long id, Recipe recipe);
    void delete(Long id);
}
