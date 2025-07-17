package dio.app.controller.dto;

import dio.app.domain.model.Recipe;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record RecipeDTO(
        Long id,
        String imageURL,
        String title,
        String description,
        List<PreparationTimeDTO> preparationTime,
        List<String> ingredients,
        List<InstructionDTO> instructions,
        NutritionDTO nutrition
) {

    public RecipeDTO(Recipe model) {
        this(
                model.getId(),
                model.getImageURL(),
                model.getTitle(),
                model.getDescription(),
                ofNullable(model.getPreparationTime()).orElse(emptyList()).stream().map(PreparationTimeDTO::new).toList(),
                model.getIngredients(),
                ofNullable(model.getInstructions()).orElse(emptyList()).stream().map(InstructionDTO::new).toList(),
                ofNullable(model.getNutrition()).map(NutritionDTO::new).orElse(null)
        );
    }

    public Recipe toModel() {
        Recipe model = new Recipe();
        model.setId(this.id);
        model.setImageURL(this.imageURL);
        model.setTitle(this.title);
        model.setDescription(this.description);

        model.setPreparationTime(
                ofNullable(this.preparationTime)
                        .orElse(emptyList())
                        .stream()
                        .map(PreparationTimeDTO::toModel)
                        .collect(Collectors.toCollection(ArrayList::new)));

        model.setIngredients(this.ingredients != null
                ? new ArrayList<>(this.ingredients)
                : new ArrayList<>());

        model.setInstructions(ofNullable(this.instructions)
                .orElse(emptyList())
                .stream()
                .map(InstructionDTO::toModel)
                .collect(Collectors.toCollection(ArrayList::new)));

        model.setNutrition(ofNullable(this.nutrition)
                .map(NutritionDTO::toModel)
                .orElse(null));

        return model;
    }
}
