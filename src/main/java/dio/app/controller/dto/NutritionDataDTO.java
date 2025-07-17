package dio.app.controller.dto;

import dio.app.domain.model.NutritionData;

public record NutritionDataDTO(Long id, String data, Integer value, String metricUnit) {
    public NutritionDataDTO(NutritionData model) {
        this(model.getId(), model.getData(), model.getValue(), model.getMetricUnit());
    }

    public NutritionData toModel() {
        NutritionData model = new NutritionData();
        model.setId(this.id);
        model.setData(this.data);
        model.setValue(this.value);
        model.setMetricUnit(this.metricUnit);

        return model;
    }
}
