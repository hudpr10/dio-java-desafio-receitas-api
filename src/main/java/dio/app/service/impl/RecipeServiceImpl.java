package dio.app.service.impl;

import dio.app.domain.model.Recipe;
import dio.app.domain.repository.RecipeRepository;
import dio.app.service.RecipeService;
import dio.app.service.exception.BusinessException;
import dio.app.service.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository repository;

    @Transactional(readOnly = true)
    public List<Recipe> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Recipe findById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Recipe create(Recipe recipe) {
        return repository.save(recipe);
    }

    @Transactional
    public Recipe update(Long id, Recipe recipe) {
        Recipe recipeFound = findById(id);

        recipeFound.setTitle(recipe.getTitle());
        recipeFound.setSlug(recipe.getSlug());
        recipeFound.setDescription(recipe.getDescription());
        recipeFound.setIngredients(recipe.getIngredients());
        recipeFound.setInstructions(recipe.getInstructions());
        recipeFound.setNutrition(recipe.getNutrition());
        recipeFound.setImageURL(recipe.getImageURL());
        recipeFound.setPreparationTime(recipe.getPreparationTime());

        return repository.save(recipeFound);
    }

    @Transactional
    public void delete(Long id) {
        Recipe recipeFound = findById(id);
        repository.delete(recipeFound);
    }
}
