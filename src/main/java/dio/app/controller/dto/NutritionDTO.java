package dio.app.controller.dto;

import dio.app.domain.model.Nutrition;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record NutritionDTO(Long id, String description, List<NutritionDataDTO> nutritionData) {
    public NutritionDTO(Nutrition model) {
        this(
                model.getId(),
                model.getDescription(),
                ofNullable(model.getNutritionData()).orElse(emptyList()).stream().map(NutritionDataDTO::new).toList()
        );
    }

    public Nutrition toModel() {
        Nutrition model = new Nutrition();
        model.setId(this.id);
        model.setDescription(this.description);
        model.setNutritionData(ofNullable(this.nutritionData)
                .orElse(emptyList())
                .stream()
                .map(NutritionDataDTO::toModel)
                .collect(Collectors.toCollection(ArrayList::new)));

        return model;
    }
}
